package net.xiaoyu233.mitemod.miteite.item.recipe;

import com.google.common.base.Objects;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.item.Materials;

import java.util.function.Consumer;

public class ForgingTableRecipes {
    private static final BiMap<RecipeKey,ForgingRecipe> RECIPES = HashBiMap.create();
    private static final Consumer<ForgingRecipe> regiseterer = forgingRecipe -> {
        RecipeKey key = new RecipeKey(forgingRecipe.getMaterial(),forgingRecipe.getLevelToUpgrade());
        RECIPES.put(key,forgingRecipe);
    };

    public static ForgingRecipe getRecipe(Material toolItem, int forgingLevel) {
        return RECIPES.get(new RecipeKey(toolItem,forgingLevel));
    }

    private static void registerThreeToSix(Material material, ForgingTableLevel level) {
        ForgingRecipe.Builder.of(material,4,level).
                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(4096).
                setHammerDurabilityCost(4096).
                setTimeReq(60 * 20).
                setQualityReward(EnumQuality.excellent).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                addMaterials(new ItemStack(Items.ingotGold,1),
                        new ItemStack(Items.ingotMithril,1),
                        new ItemStack(Items.redstone,1),
                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,5,level).
                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(4096).
                setHammerDurabilityCost(6144).
                setTimeReq(70 * 20).
                setQualityReward(EnumQuality.superb).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                addMaterials(new ItemStack(Items.ingotGold,2),
                        new ItemStack(Items.ingotMithril,1),
                        new ItemStack(Items.redstone,2),
                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,6,level).
                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(6144).
                setHammerDurabilityCost(6144).
                setTimeReq(80 * 20).
                setQualityReward(EnumQuality.superb).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                addMaterials(new ItemStack(Items.ingotGold,2),
                        new ItemStack(Items.ingotMithril,2),
                        new ItemStack(Block.blockRedstone,1),
                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
                build(regiseterer);

    }

    public static void registerAll(){
        //Copper Silver Iron Ancient metal Mithril
        registerSilverZeroToTwoRecipes(Material.silver, ForgingTableLevel.IRON);
        registerSilverZeroToTwoRecipes(Material.copper, ForgingTableLevel.IRON);
        registerZeroToTwoRecipes(Material.iron, ForgingTableLevel.IRON);
        registerZeroToTwoRecipes(Material.ancient_metal, ForgingTableLevel.MITHRIL);
        registerZeroToFourRecipes(Material.mithril, ForgingTableLevel.MITHRIL);
        //Vibranium and Enchant
        {
            registerZeroToThreeRecipes(Materials.vibranium, ForgingTableLevel.VIBRANIUM);
            registerThreeToSix(Materials.vibranium, ForgingTableLevel.VIBRANIUM);
            registerSevenToTen(Materials.vibranium, ForgingTableLevel.VIBRANIUM);

            registerZeroToThreeRecipes(Materials.enchant, ForgingTableLevel.VIBRANIUM);
            registerThreeToSix(Materials.enchant, ForgingTableLevel.VIBRANIUM);
//            registerSevenToTen(Materials.enchant, ForgingTableLevel.VIBRANIUM);
            registerEnchantSevenToTen(Materials.enchant, ForgingTableLevel.VIBRANIUM);
        }
        //Adamantium
        {
            registerZeroToThreeRecipes(Material.adamantium, ForgingTableLevel.ADAMANTIUM);
            registerThreeToSix(Material.adamantium, ForgingTableLevel.ADAMANTIUM);
        }
    }

    private static void registerSevenToTen(Material material, ForgingTableLevel level) {
        ForgingRecipe.Builder.of(Materials.vibranium,7,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(6144).
                setHammerDurabilityCost(8192).
                setTimeReq(90 * 20).
                setQualityReward(EnumQuality.masterwork).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Items.ghastTear,1),
                        new ItemStack(Items.ingotAdamantium,1),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(Materials.vibranium,8,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(8192).
                setHammerDurabilityCost(10240).
                setTimeReq(100 * 20).
                setQualityReward(EnumQuality.masterwork).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Items.ghastTear,2),
                        new ItemStack(Items.ingotAdamantium,1),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(Materials.vibranium,9,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(8192).
                setHammerDurabilityCost(10240).
                setTimeReq(110 * 20).
                setQualityReward(EnumQuality.legendary).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Items.ghastTear,2),
                        new ItemStack(Items.ingotAdamantium,2),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
    }
    private static void registerEnchantSevenToTen(Material material, ForgingTableLevel level) {
        ForgingRecipe.Builder.of(Materials.enchant,7,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(6144).
                setHammerDurabilityCost(8192).
                setTimeReq(90 * 20).
                setQualityReward(EnumQuality.masterwork).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Item.coinAdamantium,1),
                        new ItemStack(Items.ingotAdamantium,1),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(Materials.enchant,8,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(8192).
                setHammerDurabilityCost(10240).
                setTimeReq(100 * 20).
                setQualityReward(EnumQuality.masterwork).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Item.coinAdamantium,2),
                        new ItemStack(Items.ingotAdamantium,1),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(Materials.enchant,9,ForgingTableLevel.VIBRANIUM).
                setChanceOfFailure(Items.ADAMANTIUM_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(8192).
                setHammerDurabilityCost(10240).
                setTimeReq(110 * 20).
                setQualityReward(EnumQuality.legendary).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(10))).
                addMaterials(new ItemStack(Item.coinAdamantium,2),
                        new ItemStack(Items.ingotAdamantium,2),
                        new ItemStack(Item.ingotMithril,2),
                        new ItemStack(Items.ADAMANTIUM_ENHANCE_STONE,1)).
                build(regiseterer);
    }
    private static void registerZeroToFourRecipes(Material material,ForgingTableLevel level){
        registerZeroToThreeRecipes(material, level);
        ForgingRecipe.Builder.of(material,4,level).
                setChanceOfFailure(Items.MITHRIL_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(4096).
                setHammerDurabilityCost(4096).
                setTimeReq(60 * 20).
                setQualityReward(EnumQuality.excellent).
                addFaultFeedback(DowngradeFeedback.of(1)).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                addMaterials(new ItemStack(Items.ingotGold,1),
                        new ItemStack(Items.ingotMithril,1),
                        new ItemStack(Items.redstone,1),
                        new ItemStack(Items.MITHRIL_ENHANCE_STONE,1)).
                build(regiseterer);
    }

    public static void registerZeroToThreeRecipes(Material material, ForgingTableLevel level) {
        registerZeroToTwoRecipes(material,level);
        ForgingRecipe.Builder.of(material,3,level).
                //不为人知的bug
                //setChanceOfFailure(100).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(3072).
                setHammerDurabilityCost(4096).
                setTimeReq(50 * 20).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                setQualityReward(EnumQuality.excellent).
                addMaterials(new ItemStack(Items.ingotGold,2),
                        new ItemStack(Items.ingotIron,2),
                        new ItemStack(Items.IRON_ENHANCE_STONE,2)).
                build(regiseterer);

    }

    private static void registerZeroToTwoRecipes(Material material, ForgingTableLevel level) {
        ForgingRecipe.Builder.of(material,0,level).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(2048).
                setHammerDurabilityCost(2048).
                setTimeReq(20 * 20).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(3))).
                addMaterials(new ItemStack(Items.ingotGold,1),
                        new ItemStack(Items.ingotIron,1),
                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,1,level).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(2048).
                setHammerDurabilityCost(3072).
                setTimeReq(30 * 20).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                setQualityReward(EnumQuality.fine).
                addMaterials(new ItemStack(Items.ingotGold,1),
                        new ItemStack(Items.ingotIron,2),
                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,2,level).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(3072).
                setHammerDurabilityCost(3072).
                setTimeReq(40 * 20).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(3))).
                setQualityReward(EnumQuality.fine).
                addMaterials(new ItemStack(Items.ingotGold,2),
                        new ItemStack(Items.ingotIron,2),
                        new ItemStack(Items.IRON_ENHANCE_STONE,1)).
                build(regiseterer);
    }
    private static void registerSilverZeroToTwoRecipes(Material material, ForgingTableLevel level) {
        ForgingRecipe.Builder.of(material,0,level).
                setChanceOfFailure(Items.copper_enhance_stone.getFailChance()).
                setAxeDurabilityCost(1024).
                setHammerDurabilityCost(1024).
                setTimeReq(15 * 15).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(3))).
                addMaterials(new ItemStack(Items.goldNugget,3),
                        new ItemStack(Items.ingotCopper,1),
                        new ItemStack(Items.copper_enhance_stone,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,1,level).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(1024).
                setHammerDurabilityCost(2048).
                setTimeReq(20 * 15).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(5))).
                setQualityReward(EnumQuality.fine).
                addMaterials(new ItemStack(Items.goldNugget, 6),
                        new ItemStack(Items.ingotCopper,2),
                        new ItemStack(Items.copper_enhance_stone,1)).
                build(regiseterer);
        ForgingRecipe.Builder.of(material,2,level).
                setChanceOfFailure(Items.IRON_ENHANCE_STONE.getFailChance()).
                setAxeDurabilityCost(2048).
                setHammerDurabilityCost(2048).
                setTimeReq(30 * 20).
                addFaultFeedback(DurabilityFeedback.of(DurabilityFeedback.Type.ofPercentage(3))).
                setQualityReward(EnumQuality.fine).
                addMaterials(new ItemStack(Items.goldNugget,9),
                        new ItemStack(Items.ingotSilver,3),
                        new ItemStack(Items.copper_enhance_stone,1)).
                build(regiseterer);
    }


    public static class RecipeKey{
        private final Material toolItem;
        private final int levelToUpgrade;

        public RecipeKey(Material toolItem, int levelToUpgrade) {
            this.toolItem = toolItem;
            this.levelToUpgrade = levelToUpgrade;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecipeKey key = (RecipeKey) o;
            return levelToUpgrade == key.levelToUpgrade && Objects.equal(toolItem, key.toolItem);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(toolItem, levelToUpgrade);
        }
    }
}
