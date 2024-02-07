package net.xiaoyu233.mitemod.miteite.trans.item.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EnchantmentDigging.class)
public class EnchantmentDiggingMixin extends Enchantment {
    protected EnchantmentDiggingMixin(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Shadow
    public String getNameSuffix() {
        return "digging";
    }

    @Shadow
    public boolean canEnchantItem(Item item) {
        return item.getClass() == ItemPickaxe.class || item instanceof ItemAxe || item instanceof ItemShovel || item instanceof ItemHoe;
    }

    public int getNumLevelsForVibranium() {
        return 7;
    }

    @Shadow
    @Overwrite
    public boolean isOnCreativeTab(CreativeModeTab creative_tab) {
        return creative_tab == CreativeModeTab.tabTools;
    }
}
