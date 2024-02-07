package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;

import java.util.Iterator;

public class EntityZombieDoorDeep extends EntityZombie {
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;

    public EntityZombieDoorDeep(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }

    @Override
    protected void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        Item[] doorList = new Item[] {Items.doorAdamantium, Items.doorAncientMetal, Items.doorMithril};
        this.setCurrentItemOrArmor(0, (new ItemStack(doorList[Constant.GARandom.nextInt(doorList.length - 1)], 1)).randomizeForMob(this, false));
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
        this.setEntityAttribute(GenericAttributes.attackDamage, rate * 60);
        this.setEntityAttribute(GenericAttributes.maxHealth, rate * 60 + healthRate * 20);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.3D);
    }

    @Override
    public boolean canBeDisarmed() {
        return false;
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            this.dropItem(Items.voucherDoor);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32) > 3 ? 3 : (day / 32);
            for (int i1 = 0; i1 < diamond_count; i1++) {
                this.dropItem(Item.emerald);
            }
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
    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote){
            EntityLiving target = this.getAttackTarget();
            if(target instanceof EntityPlayer) {
                if (this.spawnSums < 6) {
                    if (this.spawnCounter < 20) {
                        ++this.spawnCounter;
                    } else {
                        EntityRevenant revenant = new EntityRevenant(this.worldObj);
                        if (revenant.entityId == 218) {
                            return;
                        }
                        revenant.setPosition(this.posX, this.posY, this.posZ);
                        revenant.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity)revenant);
                        revenant.onSpawnWithEgg(null);
                        revenant.addRandomWeapon();
                        revenant.setAttackTarget(this.getTarget());
                        revenant.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
                if(Configs.wenscConfig.isSpawnExchanger.ConfigValue) {
                    if(haveTryToSpawnExchanger == false) {
                        if( rand.nextInt(10) == 0) {
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
}
