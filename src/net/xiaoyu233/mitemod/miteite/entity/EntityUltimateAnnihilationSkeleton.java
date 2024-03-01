package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.xiaoyu233.fml.util.Utils;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.item.enchantment.Enchantments;
import net.xiaoyu233.mitemod.miteite.util.Configs;

public class EntityUltimateAnnihilationSkeleton extends EntitySkeleton implements IBossbarEntity {
//    private Enchantment[] enhanceSpecialBookList = new Enchantment[]{Enchantment.protection, Enchantment.sharpness, Enchantment.fortune, Enchantment.harvesting, Enchantments.EXTEND, Enchantment.efficiency, Enchantment.vampiric, Enchantment.butchering, Enchantment.featherFalling};
//    private Enchantment[] nonLevelsBookList = new Enchantment[]{Enchantments.enchantmentFixed, Enchantments.enchantmentChain, Enchantments.EMERGENCY, Enchantments.EnchantmentForge};
    private boolean attackedByPlayer;
    private int despawnCount;
    private int max_num_evasions;
    private int num_evasions;
    private boolean haveTryToSpawnExchanger = false;
    private int forced_skeleton_type = 2;
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
        this.setHeldItemStack(weapon);
        if (par1World != null && this.onServer()) {
            this.max_num_evasions = this.num_evasions = 16;
        }
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
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("AttackedByPlayer")) {
            this.attackedByPlayer = par1NBTTagCompound.getBoolean("AttackedByPlayer");
            this.max_num_evasions = par1NBTTagCompound.getByte("max_num_evasions");
            this.num_evasions = par1NBTTagCompound.getByte("num_evasions");
        }
    }

    @Override
    protected void addRandomArmor() {
//        this.setCurrentItemOrArmor(0, new ItemStack(Items.ENDING, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(1, new ItemStack(Items.unassailableHelmet, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(2, new ItemStack(Items.unassailableChestPlate, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(3, new ItemStack(Items.unassailableLeggings, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(4, new ItemStack(Items.unassailableBoots, 1).randomizeForMob(this, true));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
//        int ticks_existed_with_offset = this.getTicksExistedWithOffset();
//        if (this.onServer() && this.getHealth() > 0.0F)
//            if (this.num_evasions < this.max_num_evasions && ticks_existed_with_offset % 120 == 0) {
//                ++this.num_evasions;
//            }
//
//        if (this.hasPath() && (this.getTarget() != null || this.fleeing) && ticks_existed_with_offset % 10 == 0 && this.rand.nextInt(3) == 0) {
//            PathEntity path = this.getPathToEntity();
//            if (!path.isFinished()) {
//                int n = path.getNumRemainingPathPoints();
//                if (n > 1) {
//                    int path_index_advancement = MathHelper.clamp_int(this.rand.nextInt(n), 1, 4);
//                    PathPoint path_point = path.getPathPointFromCurrentIndex(path_index_advancement);
//                    if ((double) path_point.distanceSqTo(this) > 3.0 && this.tryTeleportTo((double) path_point.xCoord + 0.5, (double) path_point.yCoord, (double) path_point.zCoord + 0.5)) {
//                        path.setCurrentPathIndex(path.getCurrentPathIndex() + path_index_advancement - 1);
//                    }
//                }
//            }
//        }
//        if (this.attackedByPlayer) {
//            if (despawnCount < Configs.wenscConfig.annihilationSkeletonDespawnTime.ConfigValue) {
//                despawnCount++;
//            } else {
//                this.entityFX(EnumEntityFX.summoned);
//                this.setDead();
//            }
//        }
        if (particleCount > 0) {
            particleCount--;
        } else {
            this.particleCount = 20;
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
                }
            }
        }
    }

//    public boolean tryTeleportTo(double pos_x, double pos_y, double pos_z) {
//        if (!this.isDead && !(this.getHealth() <= 0.0F)) {
//            int x = MathHelper.floor_double(pos_x);
//            int y = MathHelper.floor_double(pos_y);
//            int z = MathHelper.floor_double(pos_z);
//            if (y >= 1 && this.worldObj.blockExists(x, y, z)) {
//                while(true) {
//                    --y;
//                    if (this.worldObj.isBlockSolid(x, y, z)) {
//                        ++y;
//                        if (!this.worldObj.isBlockSolid(x, y, z) && !this.worldObj.isLiquidBlock(x, y, z)) {
//                            double delta_pos_x = pos_x - this.posX;
//                            double delta_pos_y = pos_y - this.posY;
//                            double delta_pos_z = pos_z - this.posZ;
//                            AxisAlignedBB bb = this.boundingBox.translateCopy(delta_pos_x, delta_pos_y, delta_pos_z);
//                            if (this.worldObj.getCollidingBoundingBoxes(this, bb).isEmpty() && !this.worldObj.isAnyLiquid(bb)) {
//                                World var10000 = this.worldObj;
//                                double distance = (double)World.getDistanceFromDeltas(delta_pos_x, delta_pos_y, delta_pos_z);
//                                this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.runegate.ordinal()).setShort((int)(16.0 * distance)).setApproxPosition((double)MathHelper.floor_double(this.posX), (double)MathHelper.floor_double(this.posY), (double)MathHelper.floor_double(this.posZ)));
//                                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0F, 1.0F);
//                                this.setPosition(pos_x, pos_y, pos_z);
//                                this.send_position_update_immediately = true;
//                                return true;
//                            }
//
//                            return false;
//                        }
//
//                        return false;
//                    }
//
//                    if (y < 1) {
//                        return false;
//                    }
//
//                    --pos_y;
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

//    public boolean tryTeleportAwayFrom(Entity entity, double min_distance) {
//        if (!this.isDead && !(this.getHealth() <= 0.0F)) {
//            double min_distance_sq = min_distance * min_distance;
//            int x = this.getBlockPosX();
//            int y = this.getFootBlockPosY();
//            int z = this.getBlockPosZ();
//            double threat_pos_x = entity == null ? this.posX : entity.posX;
//            double threat_pos_z = entity == null ? this.posZ : entity.posZ;
//
//            for(int attempts = 0; attempts < 64; ++attempts) {
//                int dx = this.rand.nextInt(11) - 5;
//                int dy = this.rand.nextInt(9) - 4;
//                int dz = this.rand.nextInt(11) - 5;
//                if (Math.abs(dx) >= 3 || Math.abs(dz) >= 3) {
//                    int try_x = x + dx;
//                    int try_y = y + dy;
//                    int try_z = z + dz;
//                    double try_pos_x = (double)try_x + 0.5;
//                    double try_pos_z = (double)try_z + 0.5;
//                    World var10000 = this.worldObj;
//                    if (!(World.getDistanceSqFromDeltas(try_pos_x - threat_pos_x, try_pos_z - threat_pos_z) < min_distance_sq) && try_y >= 1 && this.worldObj.blockExists(try_x, try_y, try_z)) {
//                        do {
//                            --try_y;
//                        } while(!this.worldObj.isBlockSolid(try_x, try_y, try_z) && try_y >= 1);
//
//                        if (try_y >= 1) {
//                            ++try_y;
//                            if (!this.worldObj.isBlockSolid(try_x, try_y, try_z) && !this.worldObj.isLiquidBlock(try_x, try_y, try_z) && this.tryTeleportTo(try_pos_x, (double)try_y, try_pos_z)) {
//                                EntityPlayer target = this.findPlayerToAttack(Math.min(this.getMaxTargettingRange(), 24.0F));
//                                if (target != null && target != this.getTarget()) {
//                                    this.setTarget(target);
//                                }
//
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//
//            return false;
//        } else {
//            return false;
//        }
//    }

    @Override
    public boolean isHarmedByLava() {
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
        this.setEntityAttribute(GenericAttributes.maxHealth, 1000);
        this.setEntityAttribute(GenericAttributes.attackDamage, 0.0F);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.2992D);
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
            }
        }
        return super.attackEntityFrom(damage);
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            this.dropItem(Items.voucherUltimateAnnihilation);
            this.dropItemStack(new ItemStack(Items.infinityNugget,1));
//            this.dropItemStack(new ItemStack(Item.diamond,10));
//            Enchantment dropEnchantment = enhanceSpecialBookList[rand.nextInt(enhanceSpecialBookList.length)];
        }
    }

    @Override
    public boolean getCanSpawnHere(boolean perform_light_check) {
        return !this.worldObj.isOverworld()
                || (this.worldObj.getDayOfOverworld() > 32 && this.rand.nextInt(4) < 1 && this.worldObj.getClosestPlayerToEntity(this,24,true) == null && (Configs.wenscConfig.annihilationSkeletonSpawnInLight.ConfigValue || this.isValidLightLevel()));
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
