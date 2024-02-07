package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.Block;
import net.minecraft.CreativeModeTab;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.Material;

public class ItemHammer
        extends ItemTool {
    protected ItemHammer(int par1, Material material) {
        super(par1, material);
        this.setCreativeTab(CreativeModeTab.tabTools);
        this.addMaterialsEffectiveAgainst(new Material[]{Material.stone});
    }

    public float getBaseDamageVsEntity() {
        return 0.0f;
    }

    public float getBaseHarvestEfficiency(Block block) {
        return super.getBaseHarvestEfficiency(block) * 0.75f;
    }

    public float getBaseDecayRateForBreakingBlock(Block block) {
        return 3.0f;
    }

    public float getBaseDecayRateForAttackingEntity(ItemStack itemStack) {
        return 0.0f;
    }

    public String getToolType() {
        return "hammer";
    }

    public int getNumComponentsForDurability() {
        return 2;
    }

    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }
}
