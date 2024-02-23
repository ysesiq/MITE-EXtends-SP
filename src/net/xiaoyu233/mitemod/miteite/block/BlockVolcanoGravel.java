package net.xiaoyu233.mitemod.miteite.block;

import net.minecraft.AchievementList;
import net.minecraft.BlockBreakInfo;
import net.minecraft.DedicatedServer;
import net.minecraft.Item;
import net.xiaoyu233.mitemod.miteite.mixins.block.BlockGravelTrans;
import net.xiaoyu233.mitemod.miteite.util.Configs;

import java.util.Random;

public class BlockVolcanoGravel extends BlockGravelTrans {
    public BlockVolcanoGravel(int par1) {
        super(par1);
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (info.getMetadata() == 1) {
            return super.dropBlockAsEntityItem(info);
        } else if (!info.wasExploded() && info.wasHarvestedByPlayer()) {
            int fortune = info.getHarvesterFortune();
            if (fortune > 3) {
                fortune = 3;
            }
            Random rand = info.world.rand;
            int id_dropped;
            if (Configs.wenscConfig.BlnGravel.ConfigValue) {
                if (rand.nextInt(4 - fortune) > 2) {
                    return super.dropBlockAsEntityItem(info);
                }

                if (rand.nextInt(3) > 0) {
                    if (rand.nextInt(16) > 7) {
                        id_dropped = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
                    } else {
                        if (info.wasExploded()) {
                            return super.dropBlockAsEntityItem(info);
                        }

                        id_dropped = Item.shardObsidian.itemID;
                    }
                } else if (rand.nextInt(2) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardEmerald.itemID;
                } else if (rand.nextInt(2) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardDiamond.itemID;
                } else if (rand.nextInt(2) > 0) {
                    id_dropped = Item.mithrilNugget.itemID;
                }
            } else {
                if (rand.nextInt(12 - fortune * 2) > 2) {
                    return super.dropBlockAsEntityItem(info);
                }

                if (rand.nextInt(3) > 0) {
                    if (rand.nextInt(16) == 0) {
                        id_dropped = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
                    } else {
                        if (info.wasExploded()) {
                            return super.dropBlockAsEntityItem(info);
                        }

                        id_dropped = Item.chipFlint.itemID;
                    }
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.copperNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.silverNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.goldNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardObsidian.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardEmerald.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardDiamond.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.mithrilNugget.itemID;
                } else {
                    id_dropped = Item.adamantiumNugget.itemID;
                }

                if (id_dropped != -1) {
                    DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
                }

                if (info.wasHarvestedByPlayer() && (id_dropped == Item.chipFlint.itemID || id_dropped == Item.flint.itemID)) {
                    info.getResponsiblePlayer().triggerAchievement(AchievementList.flintFinder);
                }

                return this.dropBlockAsEntityItem(info, id_dropped);
            }
        } else {
            return super.dropBlockAsEntityItem(info);
        }
        return super.dropBlockAsEntityItem(info);
    }



    @Override
    public String[] getTextures() {
        return new String[0];
    }

    @Override
    public String[] getNames() {
        return new String[0];
    }
}
