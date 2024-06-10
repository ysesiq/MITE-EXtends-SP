package net.xiaoyu233.mitemod.miteite.item;

import java.util.List;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.ColorText;
import net.xiaoyu233.mitemod.miteite.util.StringUtil;

public class ItemInfinitySword extends ItemSword {

    public final int Type;

    protected ItemInfinitySword(int par1, int type) {
        super(par1, (Material) Materials.infinity);
        Type = type;
        this.addMaterialsEffectiveAgainst(new Material[]{Material.adamantium});
        this.setReachBonus(300.0F);
    }

    @Override
    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }

    @Override
    public int getNumComponentsForDurability() {
        return 4;
    }

    @Override
    public float getBaseDamageVsEntity() {
        if (Type == 0) {
            return Integer.MAX_VALUE / 20;
        } else {
            return Float.MAX_VALUE;
        }
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (Type == 0) {
            info.add(" ");
            info.add(ColorText.makeColors("极其大的伤害"));
            info.add(" ");
            super.addInformation(item_stack, player, info, extended_info, slot);
            if (item_stack.hasTagCompound()) {
                if (item_stack.getTagCompound().hasKey("tool_level")) {
//                    int tool_level = item_stack.getTagCompound().getInteger("tool_level");
//                    int maxToolLevel = this.getMaxToolLevel(item_stack);
//                    if (this.isMaxToolLevel(item_stack)) {
//                        info.add("工具等级:§6已达到最高级" + maxToolLevel);
//                    } else {
//                        info.add("工具等级:" + tool_level + "/" + maxToolLevel);
//                        if (item_stack.getTagCompound().hasKey("tool_exp")) {
//                            info.add("工具经验" + EnumChatFormat.WHITE + item_stack.getTagCompound().getInteger("tool_exp") + "/" + this.getExpReqForLevel(tool_level, this.isWeapon(item_stack.getItem())));
//                        }
//                    }
                }

                int forgingGrade;
                if (item_stack.getTagCompound().hasKey("forging_grade") && (forgingGrade = item_stack.getTagCompound().getInteger("forging_grade")) != 0) {
                    info.add("§5强化等级:§6" + StringUtil.intToRoman(forgingGrade));
                    if (extended_info) {
                        info.add("  §7装备经验增加:§a" + this.getEquipmentExpBounce(item_stack) * 100 + "%");
                        if (this.isWeapon(item_stack.getItem())) {
                            info.add("  §9攻击力增加:§6" + ItemStack.field_111284_a.format(this.getEnhancedDamage(item_stack)));
                        } else {
                            info.add("  §9挖掘速度加:§6" + ItemStack.field_111284_a.format(item_stack.getEnhanceFactor() * 100) + "%");
                        }
                    }
                }

//                if (extended_info) {
//                    info.add("§5宝石:");
//                    info.add(" §3攻击增加:§6" + ItemStack.field_111284_a.format(item_stack.getGemMaxNumeric(GemModifierTypes.damage)));
//                    NBTTagCompound compound = item_stack.stackTagCompound.getCompoundTag("modifiers");
//                    if (!compound.hasNoTags()) {
//                        info.add("工具强化:");
//                        ToolModifierTypes[] var8 = ToolModifierTypes.values();
//                        for (ToolModifierTypes value : var8) {
//                            if (compound.hasKey(value.nbtName)) {
//                                info.add("  " + value.color.toString() + value.displayName + "§r " + StringUtil.intToRoman(compound.getInteger(value.nbtName)));
//                            }
//                        }
//                    }
//                }
            }
        }
        if (Type == 1) {
            info.add(" ");
            info.add(EnumChatFormat.DARK_RED + "中二之剑,开挂获取");
        }
    }
    private float getEnhancedDamage (ItemStack itemStack){
        return this.isWeapon(itemStack.getItem()) ? (float) (itemStack.getEnhanceFactor() * (double) this.getCombinedDamageVsEntity()) : 0.0f;
    }
}

