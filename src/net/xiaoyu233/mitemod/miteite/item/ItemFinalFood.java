package net.xiaoyu233.mitemod.miteite.item;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.ColorText;

import java.util.List;

public class ItemFinalFood extends ItemFood {
    public ItemFinalFood(int par1) {
        super(par1, Material.chicken_soup, 200, 200, true, true, true, "drug_bg");
        this.setMaxStackSize(64);
//        this.setPotionEffect(MobEffectList.field_76443_y.id, 6000, 1, 1.0F);
//        //饱和  时间min  等级
//        this.setPotionEffect(MobEffectList.field_76434_w.id, 6000, 5, 1.0F);
//        //生命提升
//        this.setPotionEffect(MobEffectList.regeneration.id, 6000, 1, 1.0F);
//        this.setPotionEffect(MobEffectList.resistance.id, 6000, 255, 1.0F);
//        this.setPotionEffect(MobEffectList.fireResistance.id, 6000, 1, 1.0F);

        this.setCraftingDifficultyAsComponent(1.0f);
        this.setCreativeTab(CreativeModeTab.tabFood);
    }

    public boolean isHarmedByPepsin() {
        return false;
    }

    public boolean isHarmedByAcid() {
        return false;
    }

    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
        par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.field_76443_y.id, 36000, 1, false));
//        par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.field_76434_w.id, 6000, 5, false));
        par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.regeneration.id, 36000, 5, false));
        par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.resistance.id, 36000, 255, false));
        par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.fireResistance.id, 36000, 5, false));
        par3EntityPlayer.setInsulinResistance(par3EntityPlayer.getInsulinResistance() - 12800);
        } else {
            super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
            }
        }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(" ");
            info.add(ColorText.makeFabulous("+200饱和度"));
            info.add(ColorText.makeFabulous("+200饱食度"));
            info.add(EnumChatFormat.YELLOW + ("+160000蛋白质"));
            info.add(EnumChatFormat.GREEN + ("+160000植物营养"));
            info.add(ColorText.makeColors("-12800血糖"));
            info.add(ColorText.makeColors("+30分钟饱和，抗性提升255，生命恢复5，抗火1"));
            info.add("");
            info.add(ColorText.makeSANIC("贪婪使我强大"));
        }
    }
}
