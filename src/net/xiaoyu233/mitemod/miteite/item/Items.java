package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import net.xiaoyu233.mitemod.miteite.util.RecipeRegister;
import net.xiaoyu233.mitemod.miteite.util.ReflectHelper;

import java.util.ArrayList;
import java.util.List;

import static net.xiaoyu233.mitemod.miteite.util.ReflectHelper.createInstance;

public class Items extends Item{
    public static int shopSize = 0;
    public static List<ItemStack> priceStackList = new ArrayList();
    public static final Item BLAZE_COAL_POWDER = new ItemBlazeCoalPowder(Constant.getNextItemID());
    public static final Item DIAMOND_CHUNK = createInstance(Item.class,new Class[]{int.class,Material.class,String.class},Constant.getNextItemID(),Material.diamond,"diamond_chunk").setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.diamond) /(float)4);
    public static final Item OBSIDIAN_STICK = createInstance(Item.class,new Class[]{int.class,Material.class,String.class},Constant.getNextItemID(),Material.obsidian,"obsidian_stick");
    public static final ItemAxe VIBRANIUM_AXE = createInstance(ItemAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemBattleAxe VIBRANIUM_BATTLE_AXE = createInstance(ItemBattleAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemArmor VIBRANIUM_BOOTS = new ItemBoots(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemBow VIBRANIUM_BOW = new ItemBow(Constant.getNextItemID(),Materials.vibranium);
    public static final ItemArmor VIBRANIUM_CHESTPLATE = new ItemCuirass(Constant.getNextItemID(), Materials.vibranium, false);
    public static final ItemDagger VIBRANIUM_DAGGER = createInstance(ItemDagger.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemArmor VIBRANIUM_HELMET = new ItemHelmet(Constant.getNextItemID(),Materials.vibranium,false);
    public static final Item VIBRANIUM_INGOT = createInstance(ItemIngot.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemArmor VIBRANIUM_LEGGINGS = new ItemLeggings(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemNugget VIBRANIUM_NUGGET = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemPickaxe VIBRANIUM_PICKAXE = createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemShovel VIBRANIUM_SHOVEL = createInstance(ItemShovel.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemSword VIBRANIUM_SWORD = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.vibranium);
    public static final ItemWarHammer VIBRANIUM_WAR_HAMMER = createInstance(ItemWarHammer.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.vibranium);
    public static final ItemEnhanceStone IRON_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.iron).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.iron) * 2f);
    public static final ItemEnhanceStone MITHRIL_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.mithril).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.mithril) * 2f);
    public static final ItemEnhanceStone ADAMANTIUM_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.adamantium).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.diamond) * 2f);
    public static final ItemEnhanceStone UNIVERSAL_ENHANCE_STONE = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.universal).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.ender_pearl) * 2f);

    public static final Item lavaInPipes = new ItemLavaInPipes(Constant.getNextItemID(), Materials.lava);

    public static final Item clubIron = new ItemClubMetal(Constant.getNextItemID(), Material.iron);
    public static final Item clubMithril = new ItemClubMetal(Constant.getNextItemID(), Material.mithril);
    public static final Item clubAdamantium = new ItemClubMetal(Constant.getNextItemID(), Material.adamantium);
    public static final Item clubVibranium = new ItemClubMetal(Constant.getNextItemID(), Materials.vibranium);

    public static final Item ringKillerCopper = new ItemRingKiller(Constant.getNextItemID(), Materials.copper).setUnlocalizedName("ringKillerCopper");
    public static final Item ringKillerIron = new ItemRingKiller(Constant.getNextItemID(), Materials.iron).setUnlocalizedName("ringKillerIron");
    public static final Item ringKillerAncient = new ItemRingKiller(Constant.getNextItemID(), Materials.ancient_metal).setUnlocalizedName("ringKillerAncient");
    public static final Item ringKillerMithril = new ItemRingKiller(Constant.getNextItemID(), Materials.mithril).setUnlocalizedName("ringKillerMithril");
    public static final Item ringKillerAdamantium = new ItemRingKiller(Constant.getNextItemID(), Materials.adamantium).setUnlocalizedName("ringKillerAdamantium");
    public static final Item ringKillerVibranium = new ItemRingKiller(Constant.getNextItemID(), Materials.vibranium).setUnlocalizedName("ringKillerVibranium");
    public static final Item Stack_Torch = createInstance(Item.class, new Class[]{int.class, Material.class, String.class}, Constant.getNextItemID(), Material.circuits, "stack_torch").setCreativeTab(CreativeModeTab.tabMisc);

    public static final Item voucherExchanger = new ItemMobVoucher(Constant.getNextItemID(), "exchanger");
    public static final Item voucherDoor = new ItemMobVoucher(Constant.getNextItemID(), "door");
    public static final Item voucherZombieLord = new ItemMobVoucher(Constant.getNextItemID(), "zombie_lord");
    public static final Item voucherAnnihilationSkeleton = new ItemMobVoucher(Constant.getNextItemID(), "annihilation_skeleton");
    public static final Item voucherPigman = new ItemMobVoucher(Constant.getNextItemID(), "pigman");
    public static final Item voucherWitch = new ItemMobVoucher(Constant.getNextItemID(), "witch");
    public static final Item voucherCore = new ItemMobVoucher(Constant.getNextItemID(), "core");
    public static final Item voucherFishing = new ItemMobVoucher(Constant.getNextItemID(), "fishing");
    public static final Item voucherPlanting = new ItemMobVoucher(Constant.getNextItemID(), "planting");
    public static final Item voucherVillager = new ItemMobVoucher(Constant.getNextItemID(), "villager");
    public static final Item voucherClubCore = new ItemMobVoucher(Constant.getNextItemID(), "club_core");
    public static final Item voucherZombieBoss = new ItemMobVoucher(Constant.getNextItemID(), "zombieboss");
    public static final Item voucherGhast = new ItemMobVoucher(Constant.getNextItemID(), "ghast");
    public static final Item voucherBedrock = new ItemMobVoucher(Constant.getNextItemID(), "bedrock");
    public static final Item voucherVibraniumDoor = new ItemMobVoucher(Constant.getNextItemID(), "vibraniumdoor");
    public static final Item voucherGoldBodyCore = new ItemMobVoucher(Constant.getNextItemID(), "goldbody_core");
    public static final Item voucherSkeletonBoss = new ItemMobVoucher(Constant.getNextItemID(), "skeletonboss");
    public static final Item voucherUltimateAnnihilation = new ItemMobVoucher(Constant.getNextItemID(), "UltimateAnnihilation");

    public static final Item itemDynamicCoreIron = new ItemDynamicCore(Constant.getNextItemID(), Materials.iron, 1).setUnlocalizedName("dynamic_core_iron");
    public static final Item itemDynamicCoreAncient_metal = new ItemDynamicCore(Constant.getNextItemID(), Materials.ancient_metal, 2).setUnlocalizedName("dynamic_core_ancient_metal");
    public static final Item itemDynamicCoreMithril = new ItemDynamicCore(Constant.getNextItemID(), Materials.mithril, 3).setUnlocalizedName("dynamic_core_mithril");

    public static final Item itemDynamicCoreAdamantium = new ItemDynamicCore(Constant.getNextItemID(), Materials.adamantium, 4).setUnlocalizedName("dynamic_core_adamantium");
    public static final Item itemDynamicCoreVibranium = new ItemDynamicCore(Constant.getNextItemID(), Materials.vibranium, 5).setUnlocalizedName("dynamic_core_vibranium");
    public static final Item fancyRed = (new ItemFancyRed(Constant.getNextItemID(), Material.diamond, "fancy_red"));

    public static ItemEnhanceGem itemEnhanceGem = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 1)).setUnlocalizedName("enhance_gem_phase1");
    public static ItemEnhanceGem itemEnhanceGem2 = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 2)).setUnlocalizedName("enhance_gem_phase2");
    public static ItemEnhanceGem itemEnhanceGem3 = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 3)).setUnlocalizedName("enhance_gem_phase3");
    public static ItemEnhanceGem itemEnhanceGem4 = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 4)).setUnlocalizedName("enhance_gem_phase4");
    public static ItemEnhanceGem itemEnhanceGem5 = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 5)).setUnlocalizedName("enhance_gem_phase5");
    public static ItemEnhanceGem itemEnhanceGem6 = (ItemEnhanceGem)(new ItemEnhanceGem(Constant.getNextItemID(), 6)).setUnlocalizedName("enhance_gem_phase6");

    public static ItemEnhanceGemBox itemEnhanceGemBox = (ItemEnhanceGemBox)(new ItemEnhanceGemBox(Constant.getNextItemID())).setUnlocalizedName("enhance_gem_box_phase1");
    public static ItemGemShard itemGemShard = (ItemGemShard)(new ItemGemShard(Constant.getNextItemID())).setUnlocalizedName("gem_shard");

    public static ItemEndBook itemEndBook = (ItemEndBook)(new ItemEndBook(Constant.getNextItemID())).setUnlocalizedName("end_book");

    public static final ItemInfinitySword infinitySword = new ItemInfinitySword(Constant.getNextItemID(), 0);
    public static final Item infinityingot = (Item) ReflectHelper.createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{Constant.getNextItemID(), Materials.infinity});
    
    public static final ItemCoin coinVibranium = ReflectHelper.createInstance(ItemCoin.class, new Class[]{Integer.TYPE, Material.class}, Constant.getNextItemID(), Materials.vibranium);
    public static final Item doorVibranium = new ItemDoor(Constant.getNextItemID(), Materials.vibranium);
    public static final Item fishRodVibranium = new ItemFishingRod(Constant.getNextItemID(), Materials.vibranium);

    public static final Item clubCopper = new ItemClubMetal(Constant.getNextItemID(), Material.copper);
    public static final Item clubSilver = new ItemClubMetal(Constant.getNextItemID(),Material.silver);
    public static final Item clubAncientMetal = new ItemClubMetal(Constant.getNextItemID(),Material.ancient_metal);
    
    public static final Item ringKillerInfinity = new ItemRingKiller(Constant.getNextItemID(), Materials.infinity).setUnlocalizedName("ringKillerInfinity");
    public static final ItemInfinityArmor unassailableChestPlate = new ItemInfinityArmor(Constant.getNextItemID(), Materials.infinity,1,false);

    public static final Item enchantIngot = createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, Constant.getNextItemID(), Materials.enchant);
    public static final ItemNugget enchantNugget = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.enchant);
    public static final ItemPickaxe enchantPickaxe = createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.enchant);
    public static final ItemSword enchantSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.enchant);
    public static final Item enchantStick = createInstance(Item.class,new Class[]{int.class,Material.class,String.class},Constant.getNextItemID(), Materials.enchant, "enchant_stick");
    public static final Item enchantSRC = createInstance(Item.class,new Class[]{int.class,Material.class,String.class},Constant.getNextItemID(), Materials.enchant, "enchant_src").setXPReward(80);
    public static final Item spiderQueenSilk = new ItemSpiderQueenString(Constant.getNextItemID());
    public static final Item voucherSpiderQueen = new ItemMobVoucher(Constant.getNextItemID(), "spiderqueen");
    public static final ItemNugget infinityNugget = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.infinity);
    public static final ItemArrows arrowVibranium = new ItemArrows(Constant.getNextItemID(),Materials.vibranium);
    public static final ItemEnhanceStone copper_enhance_stone = (ItemEnhanceStone) new ItemEnhanceStone(ItemEnhanceStone.Types.copper).setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(Material.copper) * 2f);
    public static final Item clubEnchant = new ItemClubMetal(Constant.getNextItemID(),Materials.enchant);
    public static final Item itemDynamicCoreInfinity = new ItemDynamicCore(Constant.getNextItemID(), Materials.infinity, 32767).setUnlocalizedName("dynamic_core_infinity");
    public static final ItemInfinitySword ENDING = new ItemInfinitySword(Constant.getNextItemID(), 1);
    public static final ItemInfinityArmor unassailableHelmet = new ItemInfinityArmor(Constant.getNextItemID(), Materials.infinity, 0,false);
    public static final ItemInfinityArmor unassailableLeggings = new ItemInfinityArmor(Constant.getNextItemID(), Materials.infinity, 2, false);
    public static final ItemInfinityArmor unassailableBoots = new ItemInfinityArmor(Constant.getNextItemID(), Materials.infinity, 3, false);
    public static final ItemInfinityPickaxe infinityPickaxe = new ItemInfinityPickaxe(Constant.getNextItemID());
    public static final Item clubGold = new ItemClubMetal(Constant.getNextItemID(),Material.gold);
    public static final ItemFinalFood AvariceMeatBalls = new ItemFinalFood(Constant.getNextItemID());

    private static Item register(String resourceLocation, Item item, CreativeModeTab tab) {
        item.setResourceLocation(item.getResourceLocationPrefix() + resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        item.setCreativeTab(tab);
        return item;
    }

    private static Item register(String resourceLocation, Item item) {
        item.setResourceLocation(item.getResourceLocationPrefix() + resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        return item;
    }

//    @Overwrite
//    public void a(int par1, CreativeModeTab par2CreativeTabs, List par3List) {
//        par3List.add(new ItemStack(par1, 1, 0));
//    }

    public static void registerItems() {
        register("obsidian_stick", OBSIDIAN_STICK, CreativeModeTab.tabMaterials);
        register("vibranium", VIBRANIUM_INGOT, CreativeModeTab.tabMaterials);
        register("vibranium_nugget", VIBRANIUM_NUGGET, CreativeModeTab.tabMaterials);
        register("vibranium_helmet", VIBRANIUM_HELMET);
        register("vibranium_chestplate", VIBRANIUM_CHESTPLATE);
        register("vibranium_leggings", VIBRANIUM_LEGGINGS);
        register("vibranium_boots", VIBRANIUM_BOOTS);
        register("vibranium_sword", VIBRANIUM_SWORD);
        register("vibranium_shovel", VIBRANIUM_SHOVEL);
        register("vibranium_pickaxe", VIBRANIUM_PICKAXE);
        register("vibranium_axe", VIBRANIUM_AXE);
        register("vibranium_dagger", VIBRANIUM_DAGGER);
        register("vibranium_war_hammer", VIBRANIUM_WAR_HAMMER);
        register("vibranium_battle_axe", VIBRANIUM_BATTLE_AXE);
        register("bows/vibranium/", VIBRANIUM_BOW).setUnlocalizedName("vibranium_bow");
        register("vibranium_coin", coinVibranium);
        register("vibranium_door", doorVibranium);
        register("fishing_rods/vibranium_fishrod", fishRodVibranium);
        register("vibranium_arrow", arrowVibranium);

        register("enhance_stone/copper",copper_enhance_stone , CreativeModeTab.tabMaterials);
        register("enhance_stone/iron",IRON_ENHANCE_STONE , CreativeModeTab.tabMaterials);
        register("enhance_stone/mithril",MITHRIL_ENHANCE_STONE , CreativeModeTab.tabMaterials);
        register("enhance_stone/adamantium",ADAMANTIUM_ENHANCE_STONE , CreativeModeTab.tabMaterials);
        register("enhance_stone/universal",UNIVERSAL_ENHANCE_STONE , CreativeModeTab.tabMaterials);

        register("club/copper_club", clubCopper).setUnlocalizedName("copper_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/silver_club", clubSilver).setUnlocalizedName("silver_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/gold_club", clubGold).setUnlocalizedName("gold_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/iron_club", clubIron).setUnlocalizedName("iron_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/ancient_metal_club", clubAncientMetal).setUnlocalizedName("ancient_metal_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/mithril_club", clubMithril).setUnlocalizedName("mithril_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/adamantium_club", clubAdamantium).setUnlocalizedName("adamantium_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/vibranium_club", clubVibranium).setUnlocalizedName("vibranium_club").setLowestCraftingDifficultyToProduce(1.0F);
        register("club/enchant_club", clubEnchant).setUnlocalizedName("enchant_club").setLowestCraftingDifficultyToProduce(1.0F);

        register("stack_torch", Stack_Torch).setUnlocalizedName("stack_torch").setMaxStackSize(64).setCraftingDifficultyAsComponent(1.0E-9F);

        register("lava_in_pipes", lavaInPipes).setUnlocalizedName("lava_in_pipes").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_copper", ringKillerCopper).setUnlocalizedName("ring_killer_copper").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_iron", ringKillerIron).setUnlocalizedName("ring_killer_iron").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_ancient", ringKillerAncient).setUnlocalizedName("ring_killer_ancient").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_mithril", ringKillerMithril).setUnlocalizedName("ring_killer_mithril").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_adamantium", ringKillerAdamantium).setUnlocalizedName("ring_killer_adamantium").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_vibranium", ringKillerVibranium).setUnlocalizedName("ring_killer_vibranium").setLowestCraftingDifficultyToProduce(1.0F);
        register("ring_killer/ring_killer_infinity", ringKillerInfinity).setUnlocalizedName("ring_killer_infinity").setLowestCraftingDifficultyToProduce(1.0F);

        register("voucher/voucher_exchanger", voucherExchanger).setUnlocalizedName("voucher_exchanger").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_door", voucherDoor).setUnlocalizedName("voucher_door").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_zombie_lord", voucherZombieLord).setUnlocalizedName("voucher_zombie_lord").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_annihilation_skeleton", voucherAnnihilationSkeleton).setUnlocalizedName("voucher_annihilation_skeleton").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_pigman", voucherPigman).setUnlocalizedName("voucher_pigman").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_witch", voucherWitch).setUnlocalizedName("voucher_witch").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_core", voucherCore).setUnlocalizedName("voucher_core").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_fishing", voucherFishing).setUnlocalizedName("voucher_fishing").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_planting", voucherPlanting).setUnlocalizedName("voucher_planting").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_villager", voucherVillager).setUnlocalizedName("voucher_villager").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_club_core", voucherClubCore).setUnlocalizedName("voucher_club_core").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_zombieboss", voucherZombieBoss).setUnlocalizedName("voucher_zombieboss").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_ghast", voucherGhast).setUnlocalizedName("voucher_ghast").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_bedrock", voucherBedrock).setUnlocalizedName("voucher_bedrock").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_vibraniumdoor", voucherVibraniumDoor).setUnlocalizedName("voucher_vibraniumdoor").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_goldbody_core", voucherGoldBodyCore).setUnlocalizedName("voucher_goldbody_core").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_ultimateannihilation", voucherUltimateAnnihilation).setUnlocalizedName("voucher_ultimateannihilation").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_skeletonboss", voucherSkeletonBoss).setUnlocalizedName("voucher_skeletonboss").setLowestCraftingDifficultyToProduce(1.0F);
        register("voucher/voucher_spider_queen", voucherSpiderQueen).setUnlocalizedName("voucher_spiderqueen").setLowestCraftingDifficultyToProduce(1.0F);

        register("dynamic_core/dynamic_core_1", itemDynamicCoreIron).setUnlocalizedName("dynamic_core_iron").setLowestCraftingDifficultyToProduce(1.0F);
        register("dynamic_core/dynamic_core_2", itemDynamicCoreAncient_metal).setUnlocalizedName("dynamic_core_ancient_metal").setLowestCraftingDifficultyToProduce(1.0F);
        register("dynamic_core/dynamic_core_3", itemDynamicCoreMithril).setUnlocalizedName("dynamic_core_mithril").setLowestCraftingDifficultyToProduce(1.0F);
        register("dynamic_core/dynamic_core_4", itemDynamicCoreAdamantium).setUnlocalizedName("dynamic_core_adamantium").setLowestCraftingDifficultyToProduce(1.0F);
        register("dynamic_core/dynamic_core_5", itemDynamicCoreVibranium).setUnlocalizedName("dynamic_core_vibranium").setLowestCraftingDifficultyToProduce(1.0F);
        register("dynamic_core/dynamic_core_6", itemDynamicCoreInfinity).setUnlocalizedName("dynamic_core_infinity").setLowestCraftingDifficultyToProduce(1.0F);

        register("fancy_red", fancyRed).setUnlocalizedName("fancy_red").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase1", itemEnhanceGem).setUnlocalizedName("enhance_gem_phase1").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase2", itemEnhanceGem2).setUnlocalizedName("enhance_gem_phase2").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase3", itemEnhanceGem3).setUnlocalizedName("enhance_gem_phase3").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase4", itemEnhanceGem4).setUnlocalizedName("enhance_gem_phase4").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase5", itemEnhanceGem5).setUnlocalizedName("enhance_gem_phase5").setLowestCraftingDifficultyToProduce(1.0F);
        register("gem/enhance_gem_phase5", itemEnhanceGem6).setUnlocalizedName("enhance_gem_phase6").setLowestCraftingDifficultyToProduce(1.0F);

        register("diamond_chunk", DIAMOND_CHUNK, CreativeModeTab.tabMaterials);
        register("blaze_coal_powder", BLAZE_COAL_POWDER, CreativeModeTab.tabMaterials);
        register("end_book", itemEndBook, CreativeModeTab.tabTools).setUnlocalizedName("end_book");
        register("spider_queen_silk", spiderQueenSilk, CreativeModeTab.tabMaterials).setUnlocalizedName("spider_queen_silk");
        register("foods/avarice_meatballs", AvariceMeatBalls).setUnlocalizedName("avarice_meatballs");

        Items.register("ending", (Item)ENDING);
        Items.register("infinity_sword", (Item)infinitySword);
        Items.register("infinity_ingot", infinityingot);
        Items.register("unassailable_chestplate",unassailableChestPlate);
        Items.register("unassailable_helmet",unassailableHelmet);
        Items.register("unassailable_leggings",unassailableLeggings);
        Items.register("unassailable_boots",unassailableBoots);
        Items.register("infinity_pickaxe", infinityPickaxe);
        register("infinity_nugget", infinityNugget);

        register("enchant_sword", enchantSword);
        register("enchant_ingot", enchantIngot);
        register("enchant_nugget", enchantNugget);
        register("enchant_stick", enchantStick,CreativeModeTab.tabMaterials);
        register("enchant_pickaxe", enchantPickaxe);
        register("enchant_src", enchantSRC, CreativeModeTab.tabMaterials);
        Constant.initItemArray();
    }

    public static void registerRecipes(RecipeRegister register) {
        for(int j = 0; j < 16; j++){
            register.registerShapelessRecipe(new ItemStack(Item.bed, 1, j), true, new Object[] {new ItemStack(Item.dyePowder, 1, j), new ItemStack(Item.bed, 1, 0)});
        }

        register.registerShapelessRecipe(new ItemStack(Blocks.blockLantern, 1), true,Blocks.torchWood, ironNugget, ironNugget, ironNugget, ironNugget, ironNugget, ironNugget, ironNugget, ironNugget);
        register.registerShapelessRecipe(new ItemStack(Items.voucherCore, 1), true, Items.voucherAnnihilationSkeleton, Items.voucherDoor, Items.voucherExchanger, Items.voucherPigman, Items.voucherZombieLord, Items.voucherWitch);
        register.registerShapedRecipe(new ItemStack(clubCopper, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.copperNugget , '*', Items.ingotCopper});
        register.registerShapedRecipe(new ItemStack(clubSilver, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.silverNugget , '*', Items.ingotSilver});
        register.registerShapedRecipe(new ItemStack(clubGold, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.goldNugget , '*', Items.ingotGold});
        register.registerShapedRecipe(new ItemStack(clubIron, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.ironNugget , '*', Items.ingotIron});
        register.registerShapedRecipe(new ItemStack(clubAncientMetal, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.ancientMetalNugget , '*', Items.ingotAncientMetal});
        register.registerShapedRecipe(new ItemStack(clubMithril, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.mithrilNugget , '*', Items.ingotMithril});
        register.registerShapedRecipe(new ItemStack(clubAdamantium, 1), true, new Object[]{"###", "#*#"," # ", '#', Items.adamantiumNugget , '*', Items.ingotAdamantium});
        register.registerShapedRecipe(new ItemStack(clubVibranium, 1), true, new Object[]{"#B#", "#*#"," A ", '#', Items.VIBRANIUM_NUGGET , '*', Items.VIBRANIUM_INGOT, 'A', Items.voucherClubCore, 'B', Items.clubAncientMetal});
        register.registerShapedRecipe(new ItemStack(clubEnchant, 1), true, new Object[]{"#B#", "#*#"," A ", '#', Items.enchantNugget , '*', Items.enchantIngot, 'A', Items.voucherClubCore, 'B', Items.clubMithril});

        register.registerShapedRecipe(new ItemStack(itemDynamicCoreIron, 1), true, new Object[]{"ABA", "BCB","ABA", 'A', Items.ingotIron, 'B', Blocks.glass, 'C', Blocks.blockRedstone});
        register.registerShapedRecipe(new ItemStack(itemDynamicCoreAncient_metal, 1), true, new Object[]{"ABA", "BCB","ABA",'A', Items.ingotAncientMetal , 'B', Blocks.glass, 'C', Items.itemDynamicCoreIron});
        register.registerShapedRecipe(new ItemStack(itemDynamicCoreMithril, 1), true, new Object[]{"ABA", "BCB","ABA",'A', Items.ingotMithril, 'B', Blocks.glass, 'C', Items.itemDynamicCoreAncient_metal});
        register.registerShapedRecipe(new ItemStack(itemDynamicCoreAdamantium, 1), true, new Object[]{"ABA", "BCB","ABA", 'A', Items.ingotAdamantium, 'B', Blocks.glass, 'C', Items.itemDynamicCoreMithril});
        register.registerShapedRecipe(new ItemStack(itemDynamicCoreVibranium, 1), true, new Object[]{"ABA", "BCB","ABA", 'A', Items.VIBRANIUM_INGOT, 'B', Blocks.glass, 'C', Items.itemDynamicCoreAdamantium});
        register.registerShapedRecipe(new ItemStack(itemDynamicCoreInfinity, 1), true, new Object[]{"ABA", "BCB","ABA", 'A', Items.infinityingot, 'B', Blocks.glass, 'C', Items.itemDynamicCoreVibranium});

        register.registerShapelessRecipe(new ItemStack(Items.voucherClubCore, 1), true, Items.voucherFishing, Items.voucherVillager, Items.voucherPlanting);
                register.registerShapelessRecipe(new ItemStack(voucherGoldBodyCore), true,Items.voucherVibraniumDoor,Items.voucherBedrock,Items.voucherGhast,Items.voucherSkeletonBoss);
        register.registerShapelessRecipe(new ItemStack(voucherAnnihilationSkeleton), true,
                voucherDoor, voucherZombieLord, voucherWitch, voucherPigman, voucherExchanger);

                

        if(Configs.wenscConfig.isRecipeGATorch.ConfigValue) {
            for (int i = 0; i < 4; i++) {
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Block.wood, 1, i), Item.silk, Item.coal, Item.coal});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Block.wood, 1, i), Item.sinew, Item.coal, Item.coal});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Block.wood, 1, i), Item.silk, new ItemStack(Item.coal, 1, 1), new ItemStack(Item.coal, 1, 1)});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Block.wood, 1, i), Item.sinew, new ItemStack(Item.coal, 1, 1), new ItemStack(Item.coal, 1, 1)});

                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Blocks.wood1, 1, i), Item.silk, Item.coal, Item.coal});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Blocks.wood1, 1, i), Item.sinew, Item.coal, Item.coal});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Blocks.wood1, 1, i), Item.silk, new ItemStack(Item.coal, 1, 1), new ItemStack(Item.coal, 1, 1)});
                register.registerShapelessRecipe(new ItemStack(Stack_Torch, 1), true, new Object[]{new ItemStack(Blocks.wood1, 1, i), Item.sinew, new ItemStack(Item.coal, 1, 1), new ItemStack(Item.coal, 1, 1)});
            }

            register.registerShapelessRecipe(new ItemStack(Block.torchWood, 64), true, new Object[]{Stack_Torch, Stack_Torch, Stack_Torch, Stack_Torch});
            register.registerShapelessRecipe(new ItemStack(Block.torchWood, 48), true, new Object[]{Stack_Torch, Stack_Torch, Stack_Torch});
            register.registerShapelessRecipe(new ItemStack(Block.torchWood, 32), true, new Object[]{Stack_Torch, Stack_Torch});
            register.registerShapelessRecipe(new ItemStack(Block.torchWood, 16), true, new Object[]{Stack_Torch});
        }


        if(Configs.wenscConfig.isRecipeRingKiller.ConfigValue) {
            register.registerShapedRecipe(new ItemStack(ringKillerCopper, 1), true, new Object[]{"###", "#*#","###", '#', Items.swordCopper , '*', Items.emerald});
            register.registerShapedRecipe(new ItemStack(ringKillerIron, 1), true, new Object[]{"###", "#*#","###", '#', Items.swordIron , '*', Items.ringKillerCopper});
            register.registerShapedRecipe(new ItemStack(ringKillerAncient, 1), true, new Object[]{"###", "#*#","###", '#', Items.swordAncientMetal , '*', Items.ringKillerIron});
            register.registerShapedRecipe(new ItemStack(ringKillerMithril, 1), true, new Object[]{"###", "#*#","###", '#', Items.swordMithril , '*', Items.ringKillerAncient});
            register.registerShapedRecipe(new ItemStack(ringKillerAdamantium, 1), true, new Object[]{"###", "#*#","###", '#', Items.swordAdamantium , '*', Items.ringKillerMithril});
            register.registerShapedRecipe(new ItemStack(ringKillerVibranium, 1), true, new Object[]{"###", "#*#","###", '#', VIBRANIUM_SWORD , '*', Items.ringKillerAdamantium});
            register.registerShapedRecipe(new ItemStack(ringKillerInfinity, 1), true, new Object[]{"###", "#*#","###", '#', infinitySword, '*', Items.ringKillerVibranium});
        }

        for(int i =0; i < GemModifierTypes.values().length; i++) {
            register.registerShapelessRecipe(new ItemStack(itemEnhanceGem2, 1, i), true, new Object[]{new ItemStack(Items.itemGemShard, 2, 0), new ItemStack(itemEnhanceGem, 1, i)});
            register.registerShapelessRecipe(new ItemStack(itemEnhanceGem3, 1, i), true, new Object[]{new ItemStack(Items.itemGemShard, 2, 1), new ItemStack(itemEnhanceGem2, 1, i)});
            register.registerShapelessRecipe(new ItemStack(itemEnhanceGem4, 1, i), true, new Object[]{new ItemStack(Items.itemGemShard, 2, 2), new ItemStack(itemEnhanceGem3, 1, i)});
            register.registerShapelessRecipe(new ItemStack(itemEnhanceGem5, 1, i), true, new Object[]{new ItemStack(Items.itemGemShard, 2, 3), new ItemStack(itemEnhanceGem4, 1, i)});
            register.registerShapelessRecipe(new ItemStack(itemEnhanceGem6, 1, i), true, new Object[]{new ItemStack(Items.itemGemShard, 2, 4), new ItemStack(itemEnhanceGem5, 1, i)});
        }

        register.registerShapelessRecipe(new ItemStack(itemEndBook), true, Block.enderChest, Item.book);
        
        ItemCoin[] coins = new ItemCoin[]{Item.coinCopper, Item.coinSilver, Item.coinGold, Item.coinAncientMetal, Item.coinMithril, Item.coinAdamantium, coinVibranium};

        for(int i = 0; i < coins.length; ++i) {
            ItemCoin coin = coins[i];

            for(int plank_subtype = 1; plank_subtype <= 9; ++plank_subtype) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new Object[]{new ItemStack(coin, plank_subtype)});
            }

            register.registerShapelessRecipe(new ItemStack(coin), true, new Object[]{new ItemStack(coin.getNuggetPeer())});
        }

        register.registerShapelessRecipe(new ItemStack(enchantSRC), true,
                new ItemStack(Item.appleGold,1,1), Item.ingotAncientMetal, Item.expBottle, voucherFishing, VIBRANIUM_INGOT);
        register.registerShapelessRecipe(new ItemStack(infinityingot), true,
                voucherZombieBoss, VIBRANIUM_INGOT, voucherClubCore, voucherZombieBoss, voucherClubCore);
        register.registerShapelessRecipe(new ItemStack(AvariceMeatBalls), false,
                new ItemStack(Item.appleGold,8,1), Items.infinityNugget);

        register.registerShapedRecipe(new ItemStack(OBSIDIAN_STICK), true, "#", "#", '#', Block.obsidian);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_INGOT),
                false,
                "NIN",
                "IDI",
                "NIN",
                'N',
                Item.mithrilNugget,
                'I',
                Item.ingotAdamantium,
                'D',
                Items.voucherCore);
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_NUGGET, 9), true, VIBRANIUM_INGOT);
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_INGOT),
                true,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET,
                VIBRANIUM_NUGGET);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_HELMET),
                true,
                "#A#",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.helmetAdamantium).extendsNBT();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_CHESTPLATE),
                true,
                "# #",
                "#A#",
                "###",
                '#', VIBRANIUM_INGOT,
                'A', Item.plateAdamantium).extendsNBT();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_LEGGINGS),
                true,
                "#A#",
                "# #",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.legsAdamantium).extendsNBT();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BOOTS),
                true,
                "A #",
                "# #",
                '#', VIBRANIUM_INGOT,
                'A', Item.bootsAdamantium).extendsNBT();
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_PICKAXE),
                true,
                "###",
                " A ",
                " S ",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', pickaxeAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_SHOVEL),
                true,
                "#",
                "A",
                "S",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', shovelAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_SWORD),
                true,
                "#",
                "#",
                "A",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', swordAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_AXE),
                true,
                "##",
                "A#",
                "S ",
                '#', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', axeAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_DAGGER),
                true,
                "I",
                "A",
                'I', VIBRANIUM_INGOT,
                'A', daggerAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_WAR_HAMMER),
                true,
                "III",
                "IAI",
                " S ",
                'I', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', warHammerAncientMetal);
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BATTLE_AXE),
                true,
                "I I",
                "IAI",
                " S ",
                'I', VIBRANIUM_INGOT,
                'S', OBSIDIAN_STICK,
                'A', battleAxeAncientMetal);
        register.registerShapelessRecipe(new ItemStack(VIBRANIUM_INGOT, 9), true, Blocks.blockVibranium);
        register.registerShapelessRecipe(new ItemStack(Item.diamond),
                false,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK,
                DIAMOND_CHUNK);
        register.registerShapedRecipe(new ItemStack(BLAZE_COAL_POWDER),
                true,
                " B ",
                "BCB",
                " B ",
                'B', Item.blazePowder,
                'C', Item.coal);
        RecipesFurnace.smelting().addSmelting(Block.coalBlock.blockID, new ItemStack(DIAMOND_CHUNK));
        register.registerShapedRecipe(new ItemStack(VIBRANIUM_BOW),
                true,
                "NSL",
                "AVL",
                "NSL",
                'S', OBSIDIAN_STICK,
                'V', VIBRANIUM_INGOT,
                'L', spiderQueenSilk,
                'N', VIBRANIUM_NUGGET,
                'A', bowAncientMetal);
        register.registerShapedRecipe(new ItemStack(Item.emerald),
                true,
                "SS",
                "SS",
                'S', Item.shardEmerald);
        register.registerShapelessRecipe(new ItemStack(Item.shardEmerald, 4), true, Item.emerald);
        register.registerShapedRecipe(new ItemStack(IRON_ENHANCE_STONE),
                true,
                " C ",
                "SIS",
                " C ",
                'I', Item.ingotIron,
                'C', Item.ingotCopper,
                'S', Item.ingotSilver);
        register.registerShapedRecipe(new ItemStack(MITHRIL_ENHANCE_STONE),
                true,
                " I ",
                "GMG",
                " I ",
                'I', Item.ingotIron,
                'G', Item.ingotGold,
                'M', Item.ingotMithril);
        register.registerShapedRecipe(new ItemStack(ADAMANTIUM_ENHANCE_STONE),
                true,
                " F ",
                "MAM",
                " R ",
                'R', Block.blockRedstone,
                'F', Items.fancyRed,
                'M', Item.ingotMithril,
                'A', Item.ingotAdamantium);
        register.registerShapedRecipe(new ItemStack(UNIVERSAL_ENHANCE_STONE),
                true,
                "gDg",
                "bTb",
                "BEB",
                'g', Item.glowstone,
                'D', Item.bottleOfDisenchanting,
                'b', Item.blazePowder,
                'T', Item.ghastTear,
                'B', Item.book,
                'E', Item.enderPearl);
        register.registerShapedRecipe(new ItemStack(infinitySword),
                true,
                "D",
                "D",
                "E",
                'E', enchantStick,
                'D', infinityingot);

        register.registerShapedRecipe(new ItemStack(infinityPickaxe),
                true,
                "DDD",
                " E ",
                " E ",
                'E', enchantStick,
                'D', infinityingot);
        register.registerShapedRecipe(new ItemStack(doorVibranium),
                false,
                "NN ",
                "NN ",
                "NN ",
                'N',
                VIBRANIUM_INGOT);
        register.registerShapedRecipe(new ItemStack(doorVibranium),
                false,
                " NN",
                " NN",
                " NN",
                'N',
                VIBRANIUM_INGOT);
        register.registerShapedRecipe(new ItemStack(fishRodVibranium),
                false,
                "  S",
                " SL",
                "XZL",
                'L', Items.spiderQueenSilk,
                'Z', VIBRANIUM_NUGGET,
                'S', OBSIDIAN_STICK,
                'X', Item.fishingRodAncientMetal);
        register.registerShapedRecipe(new ItemStack(fishRodVibranium),
                false,
                "  X",
                " SL",
                "SZL",
                'L', Items.spiderQueenSilk,
                'Z', VIBRANIUM_NUGGET,
                'S', OBSIDIAN_STICK,
                'X', Item.fishingRodAncientMetal);
        register.registerShapedRecipe(new ItemStack(fishRodVibranium),
                false,
                "  S",
                " XL",
                "SZL",
                'L', Items.spiderQueenSilk,
                'Z', VIBRANIUM_NUGGET,
                'S', OBSIDIAN_STICK,
                'X', Item.fishingRodAncientMetal);
        register.registerShapedRecipe(new ItemStack(unassailableChestPlate),
                false,
                "IGI",
                "EBE",
                "CVC",
                'I',Items.infinityingot,
                'G',Items.voucherGoldBodyCore,
                'C',VIBRANIUM_CHESTPLATE,
                'V',Blocks.blockVibranium,
                'E',Items.coinVibranium,
                'B',Block.beacon);
        register.registerShapedRecipe(new ItemStack(enchantStick),
                false,
                "I",
                "I",
                'I',Items.enchantIngot);
        register.registerShapedRecipe(new ItemStack(enchantIngot),
                false,
                "NNN",
                "NNN",
                "NNN",
                'N',Items.enchantNugget);
        register.registerShapedRecipe(new ItemStack(enchantSword),
                false,
                "I",
                "I",
                "S",
                'I',Items.enchantIngot,
                'S',Items.OBSIDIAN_STICK);
        register.registerShapedRecipe(new ItemStack(enchantPickaxe),
                false,
                "III",
                " S ",
                " S ",
                'I',Items.enchantIngot,
                'S',Items.OBSIDIAN_STICK);
        register.registerShapedRecipe(new ItemStack(arrowVibranium,16),
                false,
                "N",
                "S",
                "F",
                'N',Items.VIBRANIUM_NUGGET,
                'S',Items.OBSIDIAN_STICK,
                'F',Item.feather);
        register.registerShapedRecipe(new ItemStack(copper_enhance_stone),
                true,
                " S ",
                "CGC",
                " S ",
                'C', Item.ingotCopper,
                'S', Item.ingotSilver,
                'G', Item.goldNugget);
        register.registerShapelessRecipe(new ItemStack(Items.infinityingot),
                false,
                infinityNugget,
                infinityNugget,
                infinityNugget,
                infinityNugget);
        register.registerShapedRecipe(new ItemStack(Items.infinityingot),
                true,
                "B B",
                " V ",
                "C C",
                'C', Items.voucherClubCore,
                'V', Items.VIBRANIUM_INGOT,
                'B', Items.voucherZombieBoss);
        register.registerShapelessRecipe(new ItemStack(enchantNugget, 9), true, enchantIngot);

        register.registerShapelessRecipe(new ItemStack(infinityNugget, 4), true, infinityingot);
        register.registerShapelessRecipe(new ItemStack(infinityingot), true, voucherZombieBoss, VIBRANIUM_INGOT, VIBRANIUM_INGOT);

        register.registerShapedRecipe(new ItemStack(Block.planks, 4, 4), true, new Object[] {"#", '#', new ItemStack(Blocks.wood1, 1, 0)});
        register.registerShapedRecipe(new ItemStack(Block.planks, 4, 5), true, new Object[] {"#", '#', new ItemStack(Blocks.wood1, 1, 1)});

        register.registerShapedRecipe(new ItemStack(Block.woodSingleSlab, 6, 4), true, new Object[] {"###", '#', new ItemStack(Block.planks, 1, 4)});
        register.registerShapedRecipe(new ItemStack(Blocks.stairsMaple, 4), true,"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 4));

        register.registerShapedRecipe(new ItemStack(Block.woodSingleSlab, 6, 5), true, new Object[] {"###", '#', new ItemStack(Block.planks, 1, 5)});
        register.registerShapedRecipe(new ItemStack(Blocks.stairsCherry, 4), true,"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 5));

        RecipesFurnace.smelting().addSmelting(enchantSRC.itemID, new ItemStack(enchantIngot));

    }
}
