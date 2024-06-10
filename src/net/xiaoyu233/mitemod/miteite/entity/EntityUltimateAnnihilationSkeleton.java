package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.xiaoyu233.fml.util.Utils;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.item.enchantment.Enchantments;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EntityUltimateAnnihilationSkeleton extends EntitySkeleton implements IBossbarEntity {
//    private Enchantment[] enhanceSpecialBookList = new Enchantment[]{Enchantment.protection, Enchantment.sharpness, Enchantment.fortune, Enchantment.harvesting, Enchantments.EXTEND, Enchantment.efficiency, Enchantment.vampiric, Enchantment.butchering, Enchantment.featherFalling};
//    private Enchantment[] nonLevelsBookList = new Enchantment[]{Enchantments.enchantmentFixed, Enchantments.enchantmentChain, Enchantments.EMERGENCY, Enchantments.EnchantmentForge};
    private boolean attackedByPlayer;
    private int despawnCount;
    private int max_num_evasions;
    private int num_evasions;
    private boolean haveTryToSpawnExchanger = false;
    private boolean hasClonePlayer = false;
    private int forced_skeleton_type = 2;
    private int thunderTick = 0;
    public int attackCounter;
    public int courseChangeCooldown;
    public int prevAttackCounter;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private int aggroCooldown;
    private Entity targetedEntity;
    private int explosionStrength;
    private boolean summonBoss = false;
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    protected float explosionRadius = 5.0F;
    private boolean has_exploded;
    public int recently_took_damage_from_conspicuous_cactus;

    private final ItemStack weapon = Utils.safeMake(() -> {
        ItemStack itemStack = new ItemStack(Items.ENDING);
        itemStack.addEnchantment(Enchantment.knockback, 10);
        itemStack.addEnchantment(Enchantments.EXTEND, 5);
        itemStack.addEnchantment(Enchantment.fireAspect,5);
        return itemStack;
        }, null);

    protected void addRandomEquipment() {
        this.setCurrentItemOrArmor(0, new ItemStack(Items.ENDING, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(1, new ItemStack(Items.unassailableHelmet, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(2, new ItemStack(Items.unassailableChestPlate, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(3, new ItemStack(Items.unassailableLeggings, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(4, new ItemStack(Items.unassailableBoots, 1).randomizeForMob(this, true));
        this.addPotionEffect(new MobEffect(1, 2147483647, 0));
        this.addPotionEffect(new MobEffect(5, 2147483647, 0));
    }

    private int particleCount;

    public EntityUltimateAnnihilationSkeleton(World par1World) {
        super(par1World);
        this.setSize(0.5F, 1.0F);
        this.setHeldItemStack(weapon);
        if (par1World != null && this.onServer()) {
            this.max_num_evasions = this.num_evasions = 16;
        }
        this.explosionStrength = 8;
    }

    @Override
    public boolean canCatchFire() {
        return false;
    }

    @Override
    public void calcFallDamage(float fall_distance, float[] damages) {
        damages[0] = 0.0F;
        damages[1] = 0.0F;
    }

    @Override
    public void setAttackTarget(EntityLiving par1EntityLivingBase) {
        super.setAttackTarget(par1EntityLivingBase);
        this.attackedByPlayer = true;
    }

    @Override
    public void setEntityToAttack(Entity par1Entity) {
        super.setEntityToAttack(par1Entity);
        this.attackedByPlayer = true;
    }

    @Override
    public void addPotionEffect(MobEffect par1PotionEffect) {

    }

//    @Override
//    public void addRandomWeapon() {
//        this.setHeldItemStack(weapon);
//    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("AttackedByPlayer", attackedByPlayer);
        par1NBTTagCompound.setByte("max_num_evasions", (byte)this.max_num_evasions);
        par1NBTTagCompound.setByte("num_evasions", (byte)this.num_evasions);
        par1NBTTagCompound.setShort("thunderTick", (short)this.thunderTick);
        par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("AttackedByPlayer")) {
            this.attackedByPlayer = par1NBTTagCompound.getBoolean("AttackedByPlayer");
            this.max_num_evasions = par1NBTTagCompound.getByte("max_num_evasions");
            this.num_evasions = par1NBTTagCompound.getByte("num_evasions");
            this.thunderTick = par1NBTTagCompound.getShort("thunderTick");
            if (par1NBTTagCompound.hasKey("ExplosionPower")) {
                this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
            }
        }
    }

    @Override
    protected void addRandomArmor() {
        this.setCurrentItemOrArmor(0, new ItemStack(Items.ENDING, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(1, new ItemStack(Items.unassailableHelmet, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(2, new ItemStack(Items.unassailableChestPlate, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(3, new ItemStack(Items.unassailableLeggings, 1).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(4, new ItemStack(Items.unassailableBoots, 1).randomizeForMob(this, true));
    }

    //僵尸boss技能:放雷,切换目标
    public void addThunderAttack(EntityPlayer player, float damage) {
        if(player != null) {
            WorldServer var20 = (WorldServer)this.worldObj;
            var20.addWeatherEffect(new EntityLightning(var20, player.posX, player.posY, player.posZ));
            player.attackEntityFrom(new Damage(DamageSource.divine_lightning, damage));
        }
    }
    public boolean setSurroundingPlayersAsTarget() {
        List entities = Arrays.asList(this.getNearbyEntities(16, 16).stream().filter(entity -> entity instanceof EntityPlayer && !((EntityPlayer) entity).isPlayerInCreative()).toArray());
        if(entities.size() > 0) {
            Object targetPlayer = entities.get(rand.nextInt(entities.size()));
            if(targetPlayer instanceof EntityPlayer) {
                this.setTarget((EntityPlayer)targetPlayer);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (particleCount > 0) {
            particleCount--;
        } else {
            this.particleCount = 100;
            this.generateRandomParticles(EnumParticle.largesmoke);
            this.generateRandomParticles(EnumParticle.largesmoke);
            this.generateRandomParticles(EnumParticle.largesmoke);
            this.generateRandomParticles(EnumParticle.largesmoke);
            this.generateRandomParticles(EnumParticle.largesmoke);
            this.generateRandomParticles(EnumParticle.largesmoke);
        }
        if (Configs.wenscConfig.isSpawnExchanger.ConfigValue) {
            if (!this.getWorld().isRemote) {
                EntityLiving target = this.getAttackTarget();
                if (target instanceof EntityPlayer) {
                    if (!haveTryToSpawnExchanger) {
                        if (rand.nextInt(50) == 0) {
                            EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
                            entityExchanger.setPosition(this.posX, this.posY, this.posZ);
                            entityExchanger.refreshDespawnCounter(-9600);
                            this.worldObj.spawnEntityInWorld(entityExchanger);
                            entityExchanger.onSpawnWithEgg(null);
                            entityExchanger.setAttackTarget(this.getTarget());
                            entityExchanger.entityFX(EnumEntityFX.summoned);
                        }
                        this.haveTryToSpawnExchanger = true;
                    }
//                    if (!summonBoss) {
//                        if (this.getHealth() >= 500) {
//                            EntityZombieBoss zombieBoss = new EntityZombieBoss(this.worldObj);
//                            zombieBoss.setPosition(this.posX, this.posY, this.posZ);
//                            zombieBoss.refreshDespawnCounter(-9600);
//                            this.worldObj.spawnEntityInWorld(zombieBoss);
//                            zombieBoss.onSpawnWithEgg(null);
//                            zombieBoss.setAttackTarget(this.getTarget());
//                            zombieBoss.entityFX(EnumEntityFX.summoned);
//                        }
//                        this.summonBoss = true;
//                    }
                }
            }
        }
        if (!this.getWorld().isRemote) {
            thunderTick ++;
            //镜像骷髅技能:复制装备
            if(ticksExisted % 20 == 0) {
                EntityLiving target = this.getTarget();

                if(target instanceof EntityPlayer) {
                    if (hasClonePlayer == false) {
                        if (target.getMaxHealth() > this.getMaxHealth()) {
                            this.setEntityAttribute(GenericAttributes.maxHealth, target.getMaxHealth());
                            this.setHealth(target.getMaxHealth());
                        }
                        this.hasClonePlayer = true;
                    }

                    Collection collection = target.getActivePotionEffects();
                    Iterator var7 = collection.iterator();
                    while (var7.hasNext()) {
                        MobEffect var8 = (MobEffect) var7.next();
                        this.addPotionEffect(new MobEffect(var8.getPotionID(), var8.getDuration()));
                    }

                    if (target.getHelmet() != null) {
                        this.setHelmet(target.getHelmet());
                    }
                    if (target.getBoots() != null) {
                        this.setBoots(target.getBoots());
                    }
                    if (target.getCuirass() != null) {
                        this.setCuirass(target.getCuirass());
                    }
                    if (target.getLeggings() != null) {
                        this.setLeggings(target.getLeggings());
                    }
                }
                //僵尸boss技能p2
                if(thunderTick % 20 == 0) {
                    if(target != null && target instanceof EntityPlayer) {
                        if(((EntityPlayer) target).isAttackByBossCounter <= 0) {
                            addThunderAttack((EntityPlayer)target, 10f);
                        }
                    }
                    if(thunderTick == 60) {
                        this.setSurroundingPlayersAsTarget();
                        thunderTick = 0;
                    }
                }
            }
        }
    }

    //恶魂领主技能:3连火焰弹
    protected void updateEntityActionState() {
        if (!super.worldObj.isRemote && super.worldObj.difficultySetting == 0) {
            super.setDead();
        }

        super.tryDespawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - super.posX;
        double var3 = this.waypointY - super.posY;
        double var5 = this.waypointZ - super.posZ;
        double var7 = var1 * var1 + var3 * var3 + var5 * var5;
        if (var7 < 1.0D || var7 > 3600.0D) {
            this.waypointX = super.posX + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = super.posY + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = super.posZ + (double)((super.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += super.rand.nextInt(5) + 2;
            var7 = MathHelper.sqrt_double(var7);
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7)) {
                super.motionX += var1 / var7 * 0.1D;
                super.motionY += var3 / var7 * 0.1D;
                super.motionZ += var5 / var7 * 0.1D;
            } else {
                this.waypointX = super.posX;
                this.waypointY = super.posY;
                this.waypointZ = super.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead) {
            this.targetedEntity = null;
        }

        if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
            this.targetedEntity = super.getClosestVulnerablePlayer(100.0D);
            if (this.targetedEntity != null) {
                this.aggroCooldown = 20;
            }
        }

        double var9 = 64.0D;
        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < var9 * var9) {
            Vec3D target_center = this.targetedEntity.getCenterPoint();
            super.renderYawOffset = super.rotationYaw = (float)MathHelper.getYawInDegrees(super.getCenterPoint(), target_center);
            if (super.canSeeEntity(this.targetedEntity)) {
                if (this.attackCounter == 8) {
                    super.worldObj.playAuxSFXAtEntity(null, 1007, (int)super.posX, (int)super.posY, (int)super.posZ, 0);
                }

                ++this.attackCounter;
                if (this.attackCounter == 15) {
                    double distance_sq = super.getCenterPoint().squareDistanceTo(target_center);
                    float lead = (float)Math.pow(distance_sq, 0.44D);
                    lead *= 0.5F + super.rand.nextFloat();
                    target_center.xCoord = this.targetedEntity.getPredictedPosX(lead);
                    target_center.zCoord = this.targetedEntity.getPredictedPosZ(lead);
                    super.worldObj.playAuxSFXAtEntity(null, 1008, (int)super.posX, (int)super.posY, (int)super.posZ, 0);

                    //Triple shot
                    for(int i = 3; i > 0; --i) {
                        EntityLargeFirebal var17 = new EntityLargeFireballNB(super.worldObj, this, target_center, 4.0F + (float)i * 2.5F);
                        if (this.worldObj.isOverworld()) {
                            var17.field_92057_e = Math.round((float)this.explosionStrength * 3.0F);
                        } else {
                            var17.field_92057_e = this.explosionStrength;
                        }

                        super.worldObj.spawnEntityInWorld(var17);
                    }

                    this.attackCounter = -35;
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        } else {
            super.renderYawOffset = super.rotationYaw = -((float)Math.atan2(super.motionX, super.motionZ)) * 180.0F / 3.1415927F;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }

        if (!super.worldObj.isRemote) {
            byte var21 = super.dataWatcher.getWatchableObjectByte(16);
            byte var12 = (byte)(this.attackCounter > 10 ? 1 : 0);
            if (var21 != var12) {
                super.dataWatcher.updateObject(16, var12);
            }
        }
        //苦力怕技能:爆炸
        if (this.recently_took_damage_from_conspicuous_cactus > 0) {
            --this.recently_took_damage_from_conspicuous_cactus;
        }

        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;
            int var2 = this.getCreeperState();
            if (var2 > 0 && this.timeSinceIgnited == 0) {
                this.playSound("random.fuse", 1.0F, 0.5F);
            }

            this.timeSinceIgnited += var2;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                if (!this.worldObj.isRemote) {
                    boolean var14 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
                    float explosion_size_vs_blocks = this.explosionRadius * 0.715F;
                    float explosion_size_vs_living_entities = this.explosionRadius * 1.1F;
                    if (this.getPowered()) {
                        this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 4.0F), this.posZ, explosion_size_vs_blocks * 2.0F, explosion_size_vs_living_entities * 2.0F, var14);
                    } else {
                        this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 4.0F), this.posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities, var14);
                    }

                    this.has_exploded = true;
//                    this.entityFX(EnumEntityFX.frags);
//                    this.setDead();
                }
            }
        }

        super.onUpdate();

    }
    public boolean getPowered() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public int getCreeperState() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
        return false;
    }


    @Override
    public boolean isHarmedByLava() {
        return false;
    }

    @Override
    public boolean isHarmedByFire() {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public void setInWeb() {

    }

    @Override
    public boolean handleWaterMovement() {
        return true;
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean canBeKnockedBack() {
        return false;
    }

    @Override
    public boolean handleLavaMovement() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, Integer.MAX_VALUE);
        this.setEntityAttribute(GenericAttributes.attackDamage, 0.0F);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.5D);
        this.setEntityAttribute(GenericAttributes.followRange, 128.0D);
    }

    @Override
    public EntityDamageResult attackEntityFrom(Damage damage) {
        boolean can_evade = !damage.isFallDamage() && !damage.isFireDamage() && !damage.isPoison();
        if (can_evade && this.num_evasions > 0) {
            --this.num_evasions;
            if (this.tryTeleportAwayFrom(this.getTarget(), 8.0)) {
                return null;
            }
            if (damage.isIndirect() || !damage.isMelee() || damage.isExplosion() || damage.isArrowDamage() || !(damage.getResponsibleEntityP() instanceof EntityPlayer)) {
                damage.setAmount(0f);
            } else if (damage.getResponsibleEntityP() instanceof EntityPlayer) {
                attackedByPlayer = true;
                if (Math.abs(this.getFootPosY() - ((EntityPlayer) damage.getResponsibleEntityP()).getFootPosY()) >= 3.0D) {
                    damage.setAmount(0f);
                    return null;
                }
                EntityWanderingWitch wanderingWitch = new EntityWanderingWitch(this.worldObj);
                wanderingWitch.setPosition(this.posX, this.posY, this.posZ);
                wanderingWitch.refreshDespawnCounter(-9600);
                this.worldObj.spawnEntityInWorld(wanderingWitch);
                wanderingWitch.onSpawnWithEgg(null);
                wanderingWitch.setAttackTarget(this.getTarget());
                wanderingWitch.entityFX(EnumEntityFX.summoned);
            }
        }
        return super.attackEntityFrom(damage);
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            this.dropItem(Items.voucherUltimateAnnihilation);
//            this.dropItemStack(new ItemStack(Items.infinityNugget,1));
//            this.dropItemStack(new ItemStack(Item.diamond,10));
//            Enchantment dropEnchantment = enhanceSpecialBookList[rand.nextInt(enhanceSpecialBookList.length)];
        }
    }

    @Override
    public boolean getCanSpawnHere(boolean perform_light_check) {
        return super.getCanSpawnHere(perform_light_check) && this.worldObj.getDayOfOverworld() >= 128;
    }

    @Override
    public boolean canSpawnInShallowWater() {
        return false;
    }

    @Override
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        return null;
    }

    @Override
    public boolean canBeDisarmed() {
        return false;
    }
//    @Override
//    public void onLivingUpdate() {
//        super.onLivingUpdate();
//        if (this.onServer() && this.getHealth() > 0.0f) {
//            int path_index_advancement;
//            PathPoint path_point;
//            int n;
//            PathEntity path;
//            int ticks_existed_with_offset = this.getTicksExistedWithOffset();
//            if (this.num_evasions < this.max_num_evasions && ticks_existed_with_offset % 100 == 0) {
//                ++this.num_evasions;
//            }
//            if (this.hasPath() && (this.getTarget() != null || this.fleeing) && ticks_existed_with_offset % 10 == 0 && this.rand.nextInt(3) == 0 && !(path = this.getPathToEntity()).isFinished() && (n = path.getNumRemainingPathPoints()) > 1 && (double)(path_point = path.getPathPointFromCurrentIndex(path_index_advancement = MathHelper.clamp_int(this.rand.nextInt(n), 1, 4))).distanceSqTo(this) > 3.0 && this.tryTeleportTo((double)path_point.xCoord + 0.5, path_point.yCoord, (double)path_point.zCoord + 0.5)) {
//                path.setCurrentPathIndex(path.getCurrentPathIndex() + path_index_advancement - 1);
//            }
//        }
//    }

    public boolean tryTeleportTo(double pos_x, double pos_y, double pos_z) {
        if (this.isDead || this.getHealth() <= 0.0f) {
            return false;
        }
        int x = MathHelper.floor_double(pos_x);
        int y = MathHelper.floor_double(pos_y);
        int z = MathHelper.floor_double(pos_z);
        if (y < 1 || !this.worldObj.blockExists(x, y, z)) {
            return false;
        }
        while (!this.worldObj.isBlockSolid(x, --y, z)) {
            if (y < 1) {
                return false;
            }
            pos_y -= 1.0;
        }
        if (this.worldObj.isBlockSolid(x, ++y, z) || this.worldObj.isLiquidBlock(x, y, z)) {
            return false;
        }
        double delta_pos_x = pos_x - this.posX;
        double delta_pos_y = pos_y - this.posY;
        double delta_pos_z = pos_z - this.posZ;
        AxisAlignedBB bb = this.boundingBox.translateCopy(delta_pos_x, delta_pos_y, delta_pos_z);
        if (!this.worldObj.getCollidingBoundingBoxes(this, bb).isEmpty() || this.worldObj.isAnyLiquid(bb)) {
            return false;
        }
        double distance = World.getDistanceFromDeltas(delta_pos_x, delta_pos_y, delta_pos_z);
        this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, new SignalData().setByte(EnumParticle.runegate.ordinal()).setShort((int)(16.0 * distance)).setApproxPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
        this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0f, 1.0f);
        this.setPosition(pos_x, pos_y, pos_z);
        this.send_position_update_immediately = true;
        return true;
    }

    public boolean tryTeleportAwayFrom(Entity entity, double min_distance) {
        if (this.isDead || this.getHealth() <= 0.0f) {
            return false;
        }
        double min_distance_sq = min_distance * min_distance;
        int x = this.getBlockPosX();
        int y = this.getFootBlockPosY();
        int z = this.getBlockPosZ();
        double threat_pos_x = entity == null ? this.posX : entity.posX;
        double threat_pos_z = entity == null ? this.posZ : entity.posZ;
        for (int attempts = 0; attempts < 64; ++attempts) {
            int dx = this.rand.nextInt(11) - 5;
            int dy = this.rand.nextInt(9) - 4;
            int dz = this.rand.nextInt(11) - 5;
            if (Math.abs(dx) < 3 && Math.abs(dz) < 3) continue;
            int try_x = x + dx;
            int try_y = y + dy;
            int try_z = z + dz;
            double try_pos_x = (double)try_x + 0.5;
            double try_pos_z = (double)try_z + 0.5;
            if (World.getDistanceSqFromDeltas(try_pos_x - threat_pos_x, try_pos_z - threat_pos_z) < min_distance_sq || try_y < 1 || !this.worldObj.blockExists(try_x, try_y, try_z)) continue;
            while (!this.worldObj.isBlockSolid(try_x, --try_y, try_z) && try_y >= 1) {
            }
            if (try_y < 1 || this.worldObj.isBlockSolid(try_x, ++try_y, try_z) || this.worldObj.isLiquidBlock(try_x, try_y, try_z) || !this.tryTeleportTo(try_pos_x, try_y, try_pos_z)) continue;
            EntityPlayer target = this.findPlayerToAttack(Math.min(this.getMaxTargettingRange(), 24.0f));
            if (target != null && target != this.getTarget()) {
                this.setTarget(target);
            }
            return true;
        }
        return false;
    }

//    @Override
//    public EntityDamageResult attackEntityFrom(Damage damage) {
//        boolean can_evade = !damage.isFallDamage() && !damage.isFireDamage() && !damage.isPoison();
//        if (can_evade && this.num_evasions > 0) {
//            --this.num_evasions;
//            if (this.tryTeleportAwayFrom(this.getTarget(), 8.0)) {
//                return null;
//            }
//        }
//        return super.attackEntityFrom(damage);
//    }
}
