package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;

import java.util.Iterator;

public class EntityZombieDoorWood extends EntityZombie {
    private boolean haveTryToSpawnExchanger = false;

    public EntityZombieDoorWood(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }

    @Override
    protected void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        this.setCurrentItemOrArmor(0, (new ItemStack(Item.doorWood, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new MobEffect(MobEffectList.moveSpeed.id, 2147483647, 0));
        if( day / 32 >= 1) {
            this.addPotionEffect(new MobEffect(MobEffectList.damageBoost.id, 2147483647, 0));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = this.getWorld().getDayOfOverworld();
        double x = day / 8 - 8;
        double rate = (0.5+ x / (20 + Math.abs(x)));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(GenericAttributes.attackDamage, rate * 50);
        this.setEntityAttribute(GenericAttributes.maxHealth, rate * 60 + healthRate * 10);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.3D);
    }

    @Override
    public boolean canBeDisarmed() {
        return false;
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
//            this.dropItem(Items.voucherDoor);
//            int day = this.getWorld().getDayOfOverworld();
//            int diamond_count = (day / 32) > 3 ? 3 : (day / 32);
//            for (int i1 = 0; i1 < diamond_count; i1++) {
//                this.dropItem(Item.emerald);
//            }
        }
    }

    @Override
    public boolean canCatchFire() {
        return false;
    }
    @Override
    public boolean isVillager() {
        return false;
    }

//    @Override
//    public void onUpdate() {
//        super.onUpdate();
//        if (!this.getWorld().isRemote){
//            EntityLiving target = this.getAttackTarget();
//            if(target instanceof EntityPlayer) {
//                if (spawnSums < 10) {
//                    if (this.spawnCounter < 200) {
//                        ++this.spawnCounter;
//                    } else {
//                       EntityZombie zombie = new EntityZombie(this.worldObj);
//                       if (zombie.entityId == 207) {
//                            return;
//                        }
//                        zombie.setPosition(this.posX, this.posY, this.posZ);
//                        zombie.refreshDespawnCounter(-9600);
//                        this.worldObj.spawnEntityInWorld(zombie);
//                        zombie.onSpawnWithEgg(null);
//                        zombie.addRandomWeapon();
//                        zombie.setAttackTarget(this.getTarget());
//                        zombie.entityFX(EnumEntityFX.summoned);
//                        this.spawnCounter = 0;
//                        ++spawnSums;
//                    }
//                }
//                if(Configs.wenscConfig.isSpawnExchanger.ConfigValue) {
//                    if(haveTryToSpawnExchanger == false) {
//                        if( rand.nextInt(10) == 0) {
//                            EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
//                            entityExchanger.setPosition(this.posX, this.posY, this.posZ);
//                            entityExchanger.refreshDespawnCounter(-9600);
//                            this.worldObj.spawnEntityInWorld(entityExchanger);
//                            entityExchanger.onSpawnWithEgg(null);
//                            entityExchanger.setAttackTarget(this.getTarget());
//                            entityExchanger.entityFX(EnumEntityFX.summoned);
//                        }
//                        this.haveTryToSpawnExchanger = true;
//                    }
//                }
//            }
//        }
//    }
}

