package net.xiaoyu233.mitemod.miteite;

import net.minecraft.ChatMessage;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormat;
import net.minecraft.HttpUtilities;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.util.ModInfo;
import net.xiaoyu233.mitemod.miteite.events.EventListeners;
import net.xiaoyu233.mitemod.miteite.mixins.MinecraftTrans;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;
import javax.swing.*;

@Mod
public class MITEITEMod extends AbstractMod {
    private static final int MOD_LOADER_MIN_VER_NUM = 130;
    private static final String MOD_LOADER_MIN_VER_STRING = "v1.3.0";
    @Override
    public void preInit() {
    }

    public static void checkUpdateVer(EntityPlayer player)  {
        String webVersion = HttpUtilities.performGetRequest("https://github.com/ysesiq/MITE-EXtends-SP/blob/master/Version.txt", 3000, 3000);
        if (webVersion == null){
            player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends-SP]:").setColor(EnumChatFormat.WHITE)
                    .appendComponent(ChatMessage.createFromText("无法检查更新")).setColor(EnumChatFormat.RED));
        } else {
            if (!Constant.MITE_ITE_VERSION.equals(webVersion)) {
                player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends-SP]: ").setColor(EnumChatFormat.WHITE)
                        .appendComponent(ChatMessage.createFromText("有新版本可用,请到Modded MITE群或者Github Release中下载")).setColor(EnumChatFormat.GREEN));
            } else {
                player.sendChatToPlayer(ChatMessage.createFromText("[MITE-EXtends-SP]: ").setColor(EnumChatFormat.WHITE)
                        .appendComponent(ChatMessage.createFromText("当前为最新版本")).setColor(EnumChatFormat.GREEN));
            }
        }
    }

    @Nonnull
    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("MITE-Extends-SP", MinecraftTrans.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public void postInit() {
        ModInfo modLoader = FishModLoader.getModsMap().get("FishModLoader");
        int modLoaderNum = modLoader.getModVerNum();
        if (modLoaderNum < MOD_LOADER_MIN_VER_NUM){
            JFrame diaFrame = new JFrame();
            diaFrame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(diaFrame,"MITE-EXtends-SP加载错误: 模组加载器版本过低\nFishModLoader模组加载器需要至少" + MOD_LOADER_MIN_VER_STRING + "版本 \n当前版本:" + modLoader.getModVerStr(),"错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        EventListeners.registerAllEvents();
        Configs.loadConfigs();
    }

    @Override
    public String modId() {
        return "MITE-EXtends-SP";
    }

    @Override
    public int modVerNum() {
        return Constant.MITE_ITE_VER_NUM;
    }

    @Override
    public String modVerStr() {
        return Constant.MITE_ITE_VERSION;
    }
}
