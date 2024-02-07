package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;

import java.util.Iterator;

public class EntityZombieMiner extends EntityZombie {
    public EntityZombieMiner(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }

    private int fx_counter;
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;


    @Override
    protected void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        this.setCurrentItemOrArmor(0, (new ItemStack(Items.VIBRANIUM_PICKAXE, 1)).randomizeForMob(this, day > 64));
//        this.setCurrentItemOrArmor(1, (new ItemStack(Items.VIBRANIUM_HELMET, 1)).randomizeForMob(this, day > 64));
//        this.setCurrentItemOrArmor(2, (new ItemStack(Items.VIBRANIUM_CHESTPLATE, 1)).randomizeForMob(this, day > 64));
//        this.setCurrentItemOrArmor(3, (new ItemStack(Items.VIBRANIUM_LEGGINGS, 1)).randomizeForMob(this, day > 64));
//        this.setCurrentItemOrArmor(4, (new ItemStack(Items.VIBRANIUM_BOOTS, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new MobEffect(1, 2147483647, 0));
        this.addPotionEffect(new MobEffect(3, 2147483647, 127));
        this.addPotionEffect(new MobEffect(5, 2147483647, 0));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = this.getWorld().getDayOfOverworld();
        double x = day / 7 - 7;
        double rate = (0.5 + x / (20 + Math.abs(x)));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(GenericAttributes.attackDamage, 12.0D + (double) day / 24.0D);
        this.setEntityAttribute(GenericAttributes.maxHealth, rate * 60 + healthRate * 15);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.3D);
    }

    @Override
    public boolean canBeDisarmed() {
        return false;
    }

    @Override
    public boolean canNeverPickUpItem(Item item_on_ground) {
        return true;
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
//            this.dropItem(Items.voucherZombieLord);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32) >= 3 ? 3 : ((day / 32) + 1);
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
        if (!this.getWorld().isRemote) {
            EntityLiving target;
            if (this.fx_counter > 0) {
                --this.fx_counter;
            } else {
                this.fx_counter = 60;
                this.entityFX(EnumEntityFX.summoned);
            }
            if (((Boolean) Configs.wenscConfig.isSpawnExchanger.ConfigValue).booleanValue() && (target = this.getAttackTarget()) instanceof EntityPlayer) {
                if (this.spawnSums < 4) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityEarthElemental elemental = new EntityEarthElemental(this.worldObj);
                        if (elemental.entityId == 207) {
                            return;
                        }
                        elemental.setPosition(this.posX, this.posY, this.posZ);
                        elemental.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) elemental);
                        elemental.onSpawnWithEgg(null);
                        elemental.setAttackTarget(this.getTarget());
                        elemental.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
//        if (!this.getWorld().isRemote){
//            if (fx_counter > 0){
//                fx_counter--;
//            }else {
//                this.fx_counter = 60;
//                this.entityFX(EnumEntityFX.summoned);
//            }
//            if (this.spawnSums < 6) {
//                if (this.spawnCounter < 20) {
//                    ++this.spawnCounter;
//                } else {
//                    EntityEarthElemental EarthElemental = new EntityEarthElemental(this.worldObj);
//                    if (EarthElemental.entityId == 219) {
//                        return;
//                    }
//                    EarthElemental.setPosition(this.posX, this.posY, this.posZ);
//                    EarthElemental.refreshDespawnCounter(-9600);
//                    this.worldObj.spawnEntityInWorld((Entity)EarthElemental);
//                    EarthElemental.onSpawnWithEgg(null);
////                    EarthElemental.addRandomWeapon();
//                    EarthElemental.setAttackTarget(this.getTarget());
//                    EarthElemental.entityFX(EnumEntityFX.summoned);
//                    this.spawnCounter = 0;
//                    ++this.spawnSums;
//                }
//            }
                if (Configs.wenscConfig.isSpawnExchanger.ConfigValue) {
//                    EntityLiving target = this.getAttackTarget();
                    if (target instanceof EntityPlayer) {
                        if (!haveTryToSpawnExchanger) {
                            if (rand.nextInt(20) == 0) {
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
}
