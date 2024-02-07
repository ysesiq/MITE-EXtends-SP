package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Configs.wenscConfig;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;

public class EntityZombieDoorLord extends EntityZombie {
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;
    private int fx_counter;

    public EntityZombieDoorLord(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }

    protected void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        this.setCurrentItemOrArmor(0, (new ItemStack(Items.doorVibranium, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(1, (new ItemStack(Items.VIBRANIUM_HELMET, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(2, (new ItemStack(Items.VIBRANIUM_CHESTPLATE, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(3, (new ItemStack(Items.VIBRANIUM_LEGGINGS, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(4, (new ItemStack(Items.VIBRANIUM_BOOTS, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new MobEffect(1, 2147483647, 0));
        this.addPotionEffect(new MobEffect(5, 2147483647, 0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = this.getWorld().getDayOfOverworld();
        double x = (double)(day / 8 - 8);
        double rate = 0.5 + x / (20.0 + Math.abs(x));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(GenericAttributes.attackDamage, rate * 50.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, rate * 500.0 + (double)(healthRate * 20));
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.6D);
    }

    public boolean canBeDisarmed() {
        return false;
    }

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(Items.voucherVibraniumDoor);
//            this.dropItem(Items.doorVibranium);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32 > 3 ? 3 : day / 32) * 2;
            for(int i1 = 0; i1 < diamond_count; ++i1) {
                this.dropItem(Items.fancyRed);
            }
        }

    }

    public boolean canCatchFire() {
        return false;
    }
    @Override
    public boolean isVillager() {
        return false;
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLiving target;
            if (this.fx_counter > 0) {
                --this.fx_counter;
            } else {
                this.fx_counter = 60;
                this.entityFX(EnumEntityFX.summoned);
            }
            if (((Boolean) Configs.wenscConfig.isSpawnExchanger.ConfigValue).booleanValue() && (target = this.getAttackTarget()) instanceof EntityPlayer) {
                if (this.spawnSums < 30) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityRevenant zombie = new EntityRevenant(this.worldObj);
                        if (zombie.entityId == 211) {
                            return;
                        }
                        zombie.setPosition(this.posX, this.posY, this.posZ);
                        zombie.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) zombie);
                        zombie.onSpawnWithEgg(null);
                        zombie.addRandomWeapon();
                        zombie.setAttackTarget(this.getTarget());
                        zombie.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
        if (!this.getWorld().isRemote) {
//            EntityLiving target = this.getAttackTarget();
            if (target instanceof EntityPlayer) {
                if (this.spawnSums < 30) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityZombie zombie = new EntityZombieDoorWood(this.worldObj);
                        if (zombie.entityId == 211) {
                            return;
                        }

                        zombie.setPosition(this.posX, this.posY, this.posZ);
                        zombie.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(zombie);
                        zombie.onSpawnWithEgg((GroupDataEntity) null);
                        zombie.addRandomWeapon();
                        zombie.setAttackTarget(this.getTarget());
                        zombie.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }

                if (wenscConfig.isSpawnExchanger.ConfigValue && !this.haveTryToSpawnExchanger) {
                    if (this.spawnSums < 10) {
                        if (this.spawnCounter < 20) {
                            ++this.spawnCounter;
                            EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
                            entityExchanger.setPosition(this.posX, this.posY, this.posZ);
                            entityExchanger.refreshDespawnCounter(-9600);
                            this.worldObj.spawnEntityInWorld(entityExchanger);
                            entityExchanger.onSpawnWithEgg((GroupDataEntity) null);
                            entityExchanger.setAttackTarget(this.getTarget());
                            entityExchanger.entityFX(EnumEntityFX.summoned);
                        }

                        this.haveTryToSpawnExchanger = true;
                    }
                }
            }
        }

    }
    @Override
    public boolean canNeverPickUpItem(Item item_on_ground) {
        return true;
    }
    @Override
    public boolean isHarmedByLava() {
        return false;
    }

    @Override
    public void setInWeb() {

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
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Overwrite
    public float getReach(EnumEntityReachContext context, Entity entity) {
        return 3.0F;
    }
}
