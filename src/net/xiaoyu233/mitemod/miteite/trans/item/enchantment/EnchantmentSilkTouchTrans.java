package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnchantmentSilkTouch.class)
public class EnchantmentSilkTouchTrans extends Enchantment  {
    protected EnchantmentSilkTouchTrans(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Overwrite
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return (super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != fortune.effectId && par1Enchantment.effectId != Enchantments.enchantmentChain.effectId);
    }

    @Shadow
    public String getNameSuffix() {
        return "untouching";
    }

    @Shadow
    public boolean canEnchantItem(Item item) {
        return item.getClass() == ItemPickaxe.class || item.getClass() == ItemShovel.class || item instanceof ItemShears || item.getClass() == ItemKnife.class || item.getClass() == ItemDagger.class;
    }

    @Shadow
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabTools;
    }
}
