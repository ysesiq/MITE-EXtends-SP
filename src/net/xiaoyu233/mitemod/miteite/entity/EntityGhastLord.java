package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityLargeFireballNB;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

public class EntityGhastLord extends EntityFlying implements IMonster {

    public int attackCounter;
    public int courseChangeCooldown;
    public int prevAttackCounter;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private int aggroCooldown;
    private int explosionStrength;
    private Entity targetedEntity;
    private int spawnCounter;
    private int spawnSums;

    public EntityGhastLord(World par1World) {
        super(par1World);
        this.setSize(4.0F, 4.0F);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.explosionStrength = 8;
        super.getEntityAttribute(GenericAttributes.maxHealth).setAttribute(200.0D);
    }

    public boolean canSpawnInShallowWater() {
        return false;
    }

    public boolean getCanSpawnHere(boolean perform_light_check) {
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere(perform_light_check);
    }

    private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
        return false;
    }

    public boolean bJ() {
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)0);
    }

    protected String getLivingSound() {
        return "mob.ghast.moan";
    }

    protected String getHurtSound() {
        return "mob.ghast.scream";
    }

    protected String getDeathSound() {
        return "mob.ghast.death";
    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("ExplosionPower")) {
            this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
        }

    }

    @Override
    public EntityDamageResult attackEntityFrom(Damage damage) {
        if (damage.isFireballFromPlayer()) {
            damage.setAmount(0.0F).setIgnoreSpecificImmunities();
        }
        EntityDamageResult result = super.attackEntityFrom(damage);
        if (result == null) {
            return result;
        } else {
            return result;
        }
    }

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

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLiving target;
            if ((target = this.getAttackTarget()) instanceof EntityPlayer) {
                if (this.spawnSums < 4) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityGhast ghast = new EntityGhast(this.worldObj);
                        ghast.setPosition(this.posX, this.posY, this.posZ);
                        ghast.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) ghast);
                        ghast.onSpawnWithEgg(null);
                        ghast.setAttackTarget(this.getTarget());
                        ghast.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }
    }
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(Items.voucherGhast);
            int day = this.getWorld().getDayOfOverworld();
            int fancyRed_count = 5 + (day / 32);
            for (int i1 = 0; i1 < fancyRed_count; i1++) {
                this.dropItem(Items.fancyRed);
            }
        }
        int num_drops = this.rand.nextInt(2);
        if (damage_source.isFireballFromPlayer() && num_drops < 1) {
            num_drops = 1;
        }
        int i;
        for (i = 0; i < num_drops; ++i) {
            this.dropItem(Item.ghastTear.itemID, 1);
        }
        num_drops = this.rand.nextInt(3);
        for (i = 0; i < num_drops; ++i) {
            this.dropItem(Item.gunpowder.itemID, 1);
        }
    }

    @Override
    public boolean isImmuneToExplosion() {
        return true;
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
    public boolean canCatchFire() {
        return false;
    }

    protected float getSoundVolume(String sound) {
        return 40.0F;
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 10;
    }

}
