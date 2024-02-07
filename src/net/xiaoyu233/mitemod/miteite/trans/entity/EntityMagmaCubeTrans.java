package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.ItemInfinitySword;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityMagmaCube.class)
public abstract class EntityMagmaCubeTrans extends EntityCubic {
    public EntityMagmaCubeTrans(World par1World) {
        super(par1World);
    }

    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(GenericAttributes.movementSpeed).setAttribute(0.20000000298023224D);
        this.getEntityAttribute(GenericAttributes.maxHealth).setAttribute(this.getMaxHealth() + 30D);
    }

    @Override
    public EntityDamageResult attackEntityAsMob(Entity target) {
        if (target != null && target.isEntityAlive() && rand.nextFloat() < Configs.wenscConfig.netherModAttackFireChance.ConfigValue){
            target.setFire(100);
        }
        return super.attackEntityAsMob(target);
    }
    @Override
    public boolean isImmuneTo(DamageSource damage_source) {
        if (!damage_source.isWater() && !damage_source.isSnowball()) {
            ItemStack item_stack = damage_source.getItemAttackedWith();
            return item_stack != null && item_stack.getItem() instanceof ItemTool && item_stack.getItemAsTool().isEffectiveAgainstBlock(Block.stone, 0) || item_stack != null && (item_stack.getItem() instanceof ItemInfinitySword) ? false : !damage_source.isExplosion();
        } else {
            return false;
        }
    }
}
