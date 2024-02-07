//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import net.xiaoyu233.mitemod.miteite.trans.entity.EntityPlayerTrans;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DedicatedServer.class)
public class DedicatedServerTrans {

    public DedicatedServerTrans() {}

    @Inject(method = "playerLoggedIn",at = @At("RETURN"))
    public void playerLoggedIn(ServerPlayer par1EntityPlayerMP, CallbackInfo callbackInfo) {
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP-RT]:").setColor(EnumChatFormat.WHITE)
                .appendComponent(ChatMessage.createFromTranslationKey("MITE-EXtends-SP-RT-" + Constant.MITE_ITE_VERSION +" 由 ")
                        .appendComponent(ChatMessage.createFromTranslationKey("Huix、Xy_Lose、Rizur").setColor(EnumChatFormat.WHITE)))
                .addText(" 重写自wensc的MITE-Extreme").setColor(EnumChatFormat.DARK_AQUA));
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP-RT]:").setColor(EnumChatFormat.RED).appendComponent(ChatMessage.createFromTranslationKey("警告：当前版本为Rewrite Test版本，可能有很多漏洞，请及时汇报")));
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP-RT]:").setColor(EnumChatFormat.RED).appendComponent(ChatMessage.createFromTranslationKey("本Mod完全免费，如果你通过付费获取本Mod，请加入Modded MITE群或B站私信Xy_Lose向我反馈")));
        //MITEITEMod.checkUpdateVer(par1EntityPlayerMP);
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP-RT]:")
                .appendComponent(ChatMessage.createFromTranslationKey("Modded MITE:  661223990，注意：此群聊需要你对MITE和Mod有一定了解才能进群").setColor(EnumChatFormat.WHITE)));
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP]:")
                .appendComponent(ChatMessage.createFromTranslationKey("更新日志:https://docs.qq.com/doc/DZVhpUGtGcFRPa2ZV").setColor(EnumChatFormat.WHITE)));
        par1EntityPlayerMP.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-EXtends-SP]:")
                .appendComponent(ChatMessage.createFromTranslationKey("感谢 ").setColor(EnumChatFormat.RED)
                        .appendComponent(ChatMessage.createFromTranslationKey("叶辉与mite-loneina").setColor(EnumChatFormat.WHITE))
                        .addText(" 对此项目的赞助").setColor(EnumChatFormat.RED)));
        if(par1EntityPlayerMP.isFirstLogin == true) {
//            ItemStack gift = new ItemStack(Item.bread,16);
//            ItemStack chest = new ItemStack(Blocks.chestAdamantium);
//            par1EntityPlayerMP.inventory.addItemStackToInventoryOrDropIt(gift);
//            par1EntityPlayerMP.inventory.addItemStackToInventoryOrDropIt(chest);
            par1EntityPlayerMP.isFirstLogin = false;
        }

//        if (par1EntityPlayerMP.isFirstLogin == true) {
//            par1EntityPlayerMP.isFirstLogin = false;
//        }
        this.updatePlayersFile();
    }
    @Shadow
    public void updatePlayersFile() {
    }
}
