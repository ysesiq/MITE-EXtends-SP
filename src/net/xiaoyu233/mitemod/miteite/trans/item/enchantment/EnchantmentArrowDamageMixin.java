package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EnchantmentArrowDamage.class)
public class EnchantmentArrowDamageMixin  extends Enchantment {
    public EnchantmentArrowDamageMixin(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Shadow
    public String getNameSuffix() {
        return "arrowDamage";
    }

    @Shadow
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemBow;
    }

    public int getNumLevelsForVibranium() {
        return 7;
    }

    @Shadow
    public boolean isOnCreativeTab(CreativeModeTab creative_tab) {
        return creative_tab == CreativeModeTab.tabCombat;
    }
}
