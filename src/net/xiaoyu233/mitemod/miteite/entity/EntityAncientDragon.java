package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.block.BlockFu;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;

import java.util.Iterator;

public class EntityAncientDragon extends EntityMonster {

    private boolean haveTryToSpawnExchanger = false;

    public EntityAncientDragon(World par1World) {
        super(par1World);
        this.setSize(0.1F, 0.1F);
        this.tasks.addTask(0, new PathfinderGoalFloat(this));
        this.tasks.addTask(2, new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.0, false));
        this.tasks.addTask(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0, true));
        this.tasks.addTask(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
        this.tasks.addTask(5, new PathfinderGoalMoveThroughVillage(this, 1.0, false));
        this.tasks.addTask(6, new PathfinderGoalRandomStroll(this, 1.0));
        this.tasks.addTask(7, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new PathfinderGoalRandomLookaround(this));
        this.targetTasks.addTask(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.tasks.addTask(2, new EntityAIMoveToFoodItem(this, 1.0F, true));
        this.tasks.addTask(4, new PathfinderGoalMeleeAttack(this, EntityAnimal.class, 1.0, true));
        this.targetTasks.addTask(3, new PathfinderGoalNearestAttackableTarget(this, EntityAnimal.class, 10, true));
        this.tasks.addTask(3, new EntityAIMoveToTree(this, 1.0F));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 12800.0D);
        this.setEntityAttribute(GenericAttributes.maxHealth, 600.0D);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 10.0D);
        this.setEntityAttribute(GenericAttributes.attackDamage, 60.0D);
    }

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(Items.AvariceMeatBalls);
        }
    }
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
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
    public int getExperienceValue() {
        return super.getExperienceValue() * 6;
    }
}
