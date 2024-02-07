package net.xiaoyu233.mitemod.miteite.trans.item;

import net.minecraft.EnumQuality;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnumQuality.class)
public class EnumQualityTrans {
//    wretched("wretched", "Wretched Quality", 0.5f),
//    poor("poor", "Poor Quality", 0.75f),
//    average("average", "Average Quality", 1.0f),
//    fine("fine", "Fine Quality", 1.25f),
//    excellent("excellent", "Excellent Quality", 1.5f),
//    superb("superb", "Superb Quality", 1.75f),
//    masterwork("masterwork", "Masterwork", 2.0f),
//    legendary("legendary", "Legendary", 2.25f);
//    private final String unlocalized_name;
//    private final float durability_modifier;
//
//    private EnumQualityTrans(String unlocalized_name, String descriptor, float durability_modifier) {
//        this.unlocalized_name = unlocalized_name;
//        this.durability_modifier = durability_modifier;
//    }

    @Overwrite
    public float getDurabilityModifier() {
        //To remove quality modifier
        return 1.0f;
//        return this.durability_modifier;
    }
}
