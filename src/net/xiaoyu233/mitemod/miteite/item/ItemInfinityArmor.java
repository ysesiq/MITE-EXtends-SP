package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.ColorText;
import net.xiaoyu233.mitemod.miteite.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.util.StringUtil;

import java.util.List;

public class ItemInfinityArmor extends ItemArmor {

    public ItemInfinityArmor(int par1, Material material,int par4, boolean is_chain_mail) {
        super(par1, material,par4, is_chain_mail);
        this.setMaxStackSize(127);
    }

    @Override
    public String getArmorType() {
        return null;
    }

    @Override
    public int getNumComponentsForDurability() {
        return 100000;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        info.add(" ");
        info.add(ColorText.makeColors("极高的防御"));
        info.add(" ");
        int forgingGrade;
        if (itemStack.hasTagCompound()) {
            int toolLevel = itemStack.getTagCompound().getInteger("tool_level");
            if (itemStack.getTagCompound().hasKey("tool_level")) {
                int maxArmorLevel = this.getMaxToolLevel(itemStack);
                if (this.isMaxToolLevel(itemStack)) {
                    info.add("装备等级:§6已达到最高级" + maxArmorLevel);
                } else {
                    info.add("装备等级:" + toolLevel + "/" + maxArmorLevel);
                    if (itemStack.getTagCompound().hasKey("tool_exp")) {
                        info.add("装备经验" + EnumChatFormat.WHITE + itemStack.getTagCompound().getInteger("tool_exp") + "/" + this.getExpReqForLevel(toolLevel, this.armorType, ReflectHelper.dyCast(this)));
                    }
                }
            }

            if (itemStack.getTagCompound().hasKey("forging_grade") && (forgingGrade = itemStack.getTagCompound().getInteger("forging_grade")) != 0) {
                info.add("§5强化等级:§6" + StringUtil.intToRoman(forgingGrade));
                if (extended_info) {
                    info.add("  §7装备经验增加:§a" +  ItemStack.field_111284_a.format(this.getEquipmentExpBounce(itemStack) * 100) + "%");
                    info.add("  §9护甲增加:§6" + ItemStack.field_111284_a.format(this.getEnhancedProtection(itemStack)));
                }
            }

            if (extended_info) {
                info.add("§5宝石:");
                info.add(" §3护甲增加:§6" + ItemStack.field_111284_a.format(itemStack.getGemMaxNumeric(GemModifierTypes.protection)));
                info.add(" §3生命增加:§6" + ItemStack.field_111284_a.format(itemStack.getGemMaxNumeric(GemModifierTypes.health)));
                info.add(" §3恢复增加:§6" + ItemStack.field_111284_a.format(itemStack.getGemMaxNumeric(GemModifierTypes.recover)));
                NBTTagCompound compound = itemStack.stackTagCompound.getCompoundTag("modifiers");
                if (!compound.hasNoTags()) {
                    info.add("装备强化:");
                    ArmorModifierTypes[] var9 = ArmorModifierTypes.values();
                    int var10 = var9.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        ArmorModifierTypes value = var9[var11];
                        if (compound.hasKey(value.nbtName)) {
                            info.add("  " + value.color.toString() + value.displayName + "§r " + StringUtil.intToRoman(compound.getInteger(value.nbtName)));
                        }
                    }
                }
            }
        }
    }
    private float getEnhancedProtection(ItemStack itemStack) {
        return (float)(itemStack.getEnhanceFactor() * (double)this.getRawProtection() * 0.68f + (double)((float)itemStack.getForgingGrade() / 3.0F)) * (0.75f);
    }
    private float getRawProtection() {
        return (float)(this.getNumComponentsForDurability() * this.getMaterialProtection()) / 24.0F;
    }
}
