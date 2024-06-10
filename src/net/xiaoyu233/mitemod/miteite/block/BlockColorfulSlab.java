package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.*;

import java.util.List;

import static net.xiaoyu233.mitemod.miteite.block.Blocks.blockColorful;

public class BlockColorfulSlab extends Block {
    private BlockSubtypes subtypes = new BlockSubtypes(new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"});
    private String[] types = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
    private IIcon side_icon;

    public BlockColorfulSlab(int id, Material material) {
        super(id, material, new BlockConstants());
        this.setMaxStackSize(64);
        this.setBlockHardness(2.0F);
        this.setCushioning(1000.0F);
        this.setResistance(10F);
        this.setCreativeTab(CreativeModeTab.tabBlock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setLightValue(0.7F);
    }

//    @Override
    public int getGroup() {
        return 0;
    }

//    @Override
    public String[] getTypes() {
        return types;
    }

//    @Override
    public Block getModelBlock(int metadata) {
        return blockColorful;
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 16;
    }

    @Override
    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata & 15;
    }

    public IIcon a(int side, int metadata) {
        Block model_block = this.getModelBlock(metadata);
        if (model_block != stone) {
            if (model_block == stoneBrick) {
                return model_block.a(side, 0);
            } else {
                if (model_block == netherBrick) {
                    side = 1;
                }

                return model_block.m(side);
            }
        } else {
            return side != 0 && side != 1 ? this.side_icon : this.cW;
        }
    }


    public String[] getTextures() {
        return this.subtypes.getTextures();
    }

    public String[] getNames() {
        return this.subtypes.getNames();
    }

    public String getFullSlabName(int metadata) {
        return super.getUnlocalizedName() + "." + this.getTypes()[this.getItemSubtype(metadata)];
    }
}
