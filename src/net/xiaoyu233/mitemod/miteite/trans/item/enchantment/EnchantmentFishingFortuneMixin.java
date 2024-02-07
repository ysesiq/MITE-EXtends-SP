package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnchantmentFishingFortune.class)
public class EnchantmentFishingFortuneMixin
extends Enchantment {
    protected EnchantmentFishingFortuneMixin(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Shadow
    public String getNameSuffix() {
        return "fishing_fortune";
    }

    @Shadow
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemFishingRod;
    }

    public int getNumLevelsForVibranium() {
      return 7;
    }

    @Shadow
    public boolean isOnCreativeTab(CreativeModeTab creative_tab) {
        return creative_tab == CreativeModeTab.tabTools;
    }
}

