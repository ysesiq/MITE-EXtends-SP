//package net.xiaoyu233.mitemod.miteite.trans.item;
//
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//
//@Mixin(value = ItemArrow.class)
//public class ItemArrowMixin extends Item {
//    public static final Material[] material_types = new Material[]{Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal};
//    public final int type_index;
//    public final Material arrowhead_material;
//    public float extraDamage;
//
//    public ItemArrowMixin(int par1, Material arrowhead_material) {
//        Material arrowhead_material1;
////        super(par1, Material.wood, "arrows/" + arrowhead_material.name + "_arrow");
//        Material[] materialArray = new Material[1];
//        arrowhead_material1 = arrowhead_material;
//        this.extraDamage = 0.0f;
//        materialArray[0] = arrowhead_material1 = null;
//        this.arrowhead_material = arrowhead_material1;
//        this.addMaterial(materialArray);
//        this.type_index = ItemArrow.getArrowIndex(arrowhead_material);
//        this.setCreativeTab(CreativeModeTab.tabCombat);
//    }
//
//    public static int getArrowIndex(Material arrowhead_material) {
//        for (int i = 0; i < material_types.length; ++i) {
//            if (material_types[i] != arrowhead_material) continue;
//            return i;
//        }
//        return -1;
//    }
//
//    public int getArrowIndex() {
//        return this.type_index;
//    }
//
//    @Overwrite
//    public float getChanceOfRecovery() {
//        if (this == arrowFlint) {
//            return 0.3f;
//        }
//        if (this == arrowObsidian) {
//            return 0.4f;
//        }
//        if (this == arrowCopper) {
//            return 0.6f;
//        }
//        if (this == arrowSilver) {
//            return 0.6f;
//        }
//        if (this == arrowRustedIron) {
//            return 0.5f;
//        }
//        if (this == arrowGold) {
//            return 0.5f;
//        }
//        if (this == arrowIron) {
//            return 0.7f;
//        }
//        if (this == arrowMithril || this == arrowAncientMetal) {
//            return 0.8f;
//        }
//        if (this == arrowAdamantium) {
//            return 0.9f;
//        }
//        if (this == arrowVibranium) {
//            return 1.0f;
//        }
//        return 0.3f;
//    }
//
//    public boolean addToEntityContainedItemsWithChance(Random rand, EntityLiving entity) {
//        if (rand.nextFloat() < this.getChanceOfRecovery()) {
//            entity.addContainedItem(this);
//            return true;
//        }
//        return false;
//    }
//
//    public float getMaterialDamageVsEntity() {
//        return this.arrowhead_material.getDamageVsEntity();
//    }
//
//    public Material getArrowheadMaterial() {
//        return this.arrowhead_material;
//    }
//
//    @Override
//    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
//        if (extended_info) {
//            info.add("");
//            info.add((Object)((Object)EnumChatFormat.BLUE) + Translator.getFormatted("item.tooltip.missileDamage", (int)this.getMaterialDamageVsEntity()));
//            if (this.arrowhead_material == Material.silver) {
//                info.add((Object)((Object)EnumChatFormat.WHITE) + Translator.get("item.tooltip.bonusVsUndead"));
//            }
//            info.add((Object)((Object)EnumChatFormat.GRAY) + Translator.getFormatted("item.tooltip.missileRecovery", (int)(this.getChanceOfRecovery() * 100.0f)));
//        }
//    }
//
////    @Override
////    public IDispenseBehavior getDispenserBehavior() {
////        return new DispenseBehaviorArrow(this);
////    }
//
//}
//
