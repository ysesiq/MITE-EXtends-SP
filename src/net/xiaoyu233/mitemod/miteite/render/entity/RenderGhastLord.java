//package net.xiaoyu233.mitemod.miteite.render.entity;
//
//import net.minecraft.*;
//import net.xiaoyu233.mitemod.miteite.entity.EntityGhastLord;
//import org.lwjgl.opengl.GL11;
//
//public class RenderGhastLord extends bhe {
//    public static final int body_texture = 0;
//    public static final int body_texture_shooting = 1;
//
//    public RenderGhastLord(bbo par1ModelBase, float par2) {
//        super(new bbi(), 2.0F);
//    }
//
//    @Override
//    protected bjo a(Entity entity) {
//        return null;
//    }
//
//    protected void setTextures() {
//        this.setTexture(0, "textures/entity/ghast/ghast");
//        this.setTexture(1, "textures/entity/ghast/ghast_shooting");
//    }
//
////    protected bjo func_110867_a(EntityGhastLord par1EntityGhast)
////    {
////        return par1EntityGhast.func_110182_bF() ? this.textures[1] : this.textures[0];
////    }
//
//    protected void preRenderGhast(EntityGhast par1EntityGhast, float par2) {
//        float var3 = ((float)par1EntityGhast.prevAttackCounter + (float)(par1EntityGhast.attackCounter - par1EntityGhast.prevAttackCounter) * par2) / 20.0F;
//        if (var3 < 0.0F) {
//            var3 = 0.0F;
//        }
//        var3 = 1.0F / (var3 * var3 * var3 * var3 * var3 * 2.0F + 1.0F);
//        float var4 = (8.0F + var3) / 2.0F;
//        float var5 = (8.0F + 1.0F / var3) / 2.0F;
//        GL11.glScalef(var5, var4, var5);
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//    }
//
//
////    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
////        this.preRenderGhast((EntityGhastLord)par1EntityLivingBase, par2);
////    }
//
//
////    protected ResourceLocation getEntityTexture(Entity par1Entity) {
////        return this.func_110867_a((EntityGhastLord)par1Entity);
////    }
//}
