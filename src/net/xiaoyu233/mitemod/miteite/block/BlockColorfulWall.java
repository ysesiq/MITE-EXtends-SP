package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.*;

public class BlockColorfulWall extends BlockCobbleWall {

    public static final String[] types = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};

    private BlockSubtypes subtypes = new BlockSubtypes(new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"});

    public BlockColorfulWall(int par1, Block par2Block, int par3) {
        super(par1, par2Block);
        this.setMaxStackSize(64);
        this.setLightValue(0.7F);
    }

    public boolean isAlwaysReplaceable() {
        return false;
    }

    public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
        return false;
    }

    public int n() {
        return 0;
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 16;
    }

//    public int getBlockSubtypeUnchecked(int metadata) {
//        return metadata;
//    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 15;
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return this.dropBlockAsEntityItem(info, this.createStackedBlock(info.getMetadata()));
    }

    public void a(mt par1IconRegister) {
        this.subtypes.setIcons(this.registerIcons(par1IconRegister, this.getTextures(), "colorful/colorful_"));
    }

    public IIcon a(int side, int metadata) {
        return this.subtypes.getIcon(this.getBlockSubtype(metadata));
    }

    public String[] getTextures() {
        return this.subtypes.getTextures();
    }

    public String[] getNames() {
        return this.subtypes.getNames();
    }

}
