package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.*;

import java.util.List;

import static net.xiaoyu233.mitemod.miteite.block.Blocks.blockColorful;

public class BlockColorfulSlab extends BlockSlab {


    public BlockColorfulSlab(int id, Material material) {
        super(id, material);
        this.setMaxStackSize(64);
        this.setBlockHardness(2.0F);
        this.setCushioning(1000.0F);
        this.setResistance(10F);
        this.setCreativeTab(CreativeModeTab.tabBlock);
        this.setStepSound(Block.soundStoneFootstep);
        this.setLightValue(0.7F);
    }

    @Override
    public int getGroup() {
        return 0;
    }

    @Override
    public String[] getTypes() {
        return new String[0];
    }

    @Override
    public Block getModelBlock(int i) {
        return blockColorful;
    }

    @Override
    public int getBlockSubtypeUnchecked(int i) {
        return 0;
    }
}
