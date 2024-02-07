package net.xiaoyu233.mitemod.miteite.item.enchantment;

import net.minecraft.CreativeModeTab;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.yq;
import net.xiaoyu233.mitemod.miteite.item.ItemInfinityArmor;

public class EnchantmentUnassailable extends Enchantment {
    protected EnchantmentUnassailable(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }
    @Override
    public int getNumLevels() {
        return 1;
    }

    @Override
    public String getNameSuffix() {
        return "unassailable";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemInfinityArmor;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab var1) {
        return var1 == CreativeModeTab.tabCombat;
    }
}
