package net.xiaoyu233.mitemod.miteite.trans.command;

import net.minecraft.CommandAbstract;
import net.minecraft.CommandOp;
import net.minecraft.ICommandListener;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;

@Mixin(CommandOp.class)
public class CommandOpMixin extends CommandAbstract {
    @Overwrite
    public String getCommandName() {
        return "op";
    }

    @Overwrite
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Overwrite
    public String getCommandUsage(ICommandListener par1ICommandSender) {
        return "commands.op.usage";
    }


    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        if (strings.length == 1) {
            String var3 = strings[strings.length - 1];
            ArrayList var4 = new ArrayList();
            String[] var5 = MinecraftServer.F().getAllUsernames();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String var8 = var5[var7];
                if (!MinecraftServer.F().getConfigurationManager().isPlayerOpped(var8) && doesStringStartWith(var3, var8)) {
                    var4.add(var8);
                }
            }
        }
    }
}



