package net.xiaoyu233.mitemod.miteite.trans.block;

import net.minecraft.BitHelper;
import net.minecraft.Block;
import net.minecraft.BlockOre;
import net.minecraft.BlockSubtypes;
import net.minecraft.Entity;
import net.minecraft.EnumFace;
import net.minecraft.IBlockWithSubtypes;
import net.minecraft.IIcon;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.World;
import net.minecraft.mt;

public class BlockGoldOreMixin
        extends BlockOre
        implements IBlockWithSubtypes {
    private BlockSubtypes subtypes = new BlockSubtypes(new String[]{"gold_ore", "gold_ore_netherrack"});

    public BlockGoldOreMixin(int par1, Material vein_material, int min_harvest_level) {
        super(par1, vein_material, min_harvest_level);
    }

    @Override
    public String getMetadataNotes() {
        return "0=Gold Ore Stone, 2=Gold Ore Netherrack, bit 1 set if placed by entity";
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 4;
    }

    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return BitHelper.isBitSet(metadata, 2) ? 1 : 0;
    }

    @Override
    public int getItemSubtype(int metadata) {
        return this.getBlockSubtype(metadata) == 1 ? 2 : 0;
    }

    public boolean isGoldOreNetherrack(int metadata) {
        return BlockGoldOreMixin.isGoldOreNetherrack(this, metadata);
    }

    public static boolean isGoldOreNetherrack(Block block, int metadata) {
        return block == oreGold && block.getBlockSubtype(metadata) == 1;
    }

    @Override
    public void a(mt par1IconRegister) {
        this.subtypes.setIcons(this.registerIcons(par1IconRegister, this.getTextures()));
    }

    @Override
    public IIcon a(int side, int metadata) {
        return this.subtypes.getIcon(this.getBlockSubtype(metadata));
    }

    @Override
    public String[] getTextures() {
        return this.subtypes.getTextures();
    }

    @Override
    public String[] getNames() {
        return this.subtypes.getNames();
    }

    @Override
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        return item_stack.getItemSubtype() | 1;
    }
}
