package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemRock.class)
public class ItemGemsTrans extends Item {
   @Overwrite
   public static int getExperienceValueWhenSacrificed(ItemStack item_stack) {
      Item item = item_stack.getItem();
      if (item == Item.dyePowder && item_stack.getItemSubtype() == 4) {
         return 40;
      } else {
         return item == netherQuartz
                 ? Configs.wenscConfig.quartzExp.ConfigValue
                 : (item == emerald ? Configs.wenscConfig.emeraldExp.ConfigValue
                 : (item == diamond ? Configs.wenscConfig.diamondExp.ConfigValue
                 : (item == Items.fancyRed ? Configs.wenscConfig.fancyRedExp.ConfigValue
                 : 0)));
      }
   }

   @Overwrite
   public static boolean onItemRightClick(EntityPlayer player, ItemStack item_stack, float partial_tick, boolean ctrl_is_down) {
      int xp_value = getExperienceValueWhenSacrificed(item_stack);
      if (xp_value < 1) {
         return false;
      } else {
         if (player.onServer()) {
            Item var10001 = item_stack.getItem();
            PlayerInventory var10002 = player.inventory;
            if (item_stack.getItem() == netherQuartz && player.getExperienceLevel() >= Configs.wenscConfig.quartzMaxExpLevel.ConfigValue) {
               return false;
            }
            if(ctrl_is_down){
               int amount = player.getHeldItemStack().stackSize;
               player.causeBreakingItemEffect(var10001, var10002.currentItem);
               player.setHeldItemStack(null);
               player.addExperience(xp_value * amount);
            }else {
               player.causeBreakingItemEffect(var10001, var10002.currentItem);
               player.convertOneOfHeldItem((ItemStack)null);
               player.addExperience(xp_value);
            }

         } else {
            player.bobItem();
         }

         return true;
      }
   }

   public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
      int xp_value = getExperienceValueWhenSacrificed(item_stack);
      if (extended_info && xp_value > 0) {
         info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("item.tooltip.XPEach", new Object[]{xp_value}));
         info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("Ctrl+右键使用一组"));
      }

   }

   @Inject(
           method = "onItemRightClick(Lnet/minecraft/EntityPlayer;Lnet/minecraft/ItemStack;FZ)Z",
           at = @At(value = "INVOKE",
                   shift = At.Shift.BEFORE,
                   target = "Lnet/minecraft/EntityPlayer;causeBreakingItemEffect(Lnet/minecraft/Item;I)V"),
           cancellable = true)
   private static void injectNetherQuartzLimit(EntityPlayer player, ItemStack item_stack,float partial_tick,boolean ctrl_is_down,CallbackInfoReturnable<Boolean> callbackInfoReturnable){
      if (item_stack.getItem() == netherQuartz && player.getExperienceLevel() >= Configs.wenscConfig.quartzMaxExpLevel.ConfigValue) {
         callbackInfoReturnable.setReturnValue(false);
         callbackInfoReturnable.cancel();
      }
   }
}