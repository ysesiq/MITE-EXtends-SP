package net.xiaoyu233.mitemod.miteite.trans.gui;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;
import java.awt.Color;
import java.util.List;



@Mixin(GuiIngame.class)
public class GuiPlayerOverlayTrans extends avk {
   @Shadow
   @Final
   private static final bjo b = new bjo("textures/misc/vignette.png");
   @Shadow
   @Final
   private static final bjo c = new bjo("textures/gui/widgets.png");
   @Shadow
   @Final
   private static final bjo d = new bjo("textures/misc/pumpkinblur.png");
   @Shadow
   @Final
   private static final bgw e = new bgw();
   @Shadow
   @Final
   protected static final bjo MITE_icons = new bjo("textures/gui/MITE_icons.png");

   public int currentItem;
   public ItemStack[] mainInventory = new ItemStack[36];

   @Shadow
   @Final
   public static int allotted_time = -1;
   @Shadow
   @Final
   public static int server_load = -1;
   @Final
   private final Random f = new Random();
   @Shadow
   @Final
   private Minecraft g;
   @Shadow
   @Final
   private auu h;
   @Shadow
   @Final
   private int i;
   @Shadow
   @Final
   private String j;
   @Shadow
   @Final
   private String last_highlighting_item_stack_text;
   @Shadow
   @Final
   private int o;
   @Shadow
   @Final
   private boolean p;
   @Shadow
   @Final
   private int q;
   @Shadow
   @Final
   private ItemStack r;
   @Shadow
   @Final
   public static long display_overburdened_cpu_icon_until_ms;
   @Shadow
   @Final
   public int curse_notification_counter;
   private String overlayMsg;
   private int overlayMsgColor;
   private int overlayMsgDisplayTime;

   public void b(int x, int y, int textureX, int textureY, int width, int height) {
      super.b(x, y, textureX, textureY, width, height);
   }

   @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
           method = "a(II)V",
           at = @At(value = "INVOKE_STRING",
                   target = "endStartSection(Ljava/lang/String;)V",
                   args = "ldc=air",
                   shift = At.Shift.BEFORE))
   private void injectRenderNutrition(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodMetaData var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
      float protein = (float) this.g.h.getProtein();
      float phytonutrients = (float) this.g.h.getPhytonutrients();
      int var26 = var12 + 240 + Configs.wenscConfig.healthBarXOffset.ConfigValue;
      int var25 = var13 + 32 + Configs.wenscConfig.healthBarYOffset.ConfigValue;
      GL11.glPushMatrix();
      GL11.glPushMatrix();
      this.b(this.g.l, (int) phytonutrients + "/" + 160000 + " (" + (int) (phytonutrients / 1600.0f) + "%)", var26 - 177, var25 - 8, 16777215);
      GL11.glScalef(0.6F, 1.0F, 1.0F);
      this.g.J().a(Constant.icons_ite);
      this.b(var26, var25, 0, 106, 182, 6);
      this.b(var26, var25, 0, 94, (int) (182.0F * (phytonutrients / 160000.0F)), 6);
      GL11.glPopMatrix();
      var26 = var12 - 303;
      GL11.glPushMatrix();
      this.b(this.g.l, (int) protein + "/" + 160000 + " (" + (int) (protein / 1600.0f) + "%)", var26, var25 - 8, 16777215);
      GL11.glScalef(0.6F, 1.0F, 1.0F);
      this.g.J().a(Constant.icons_ite);
      this.b(var26, var25, 0, 106, 182, 6);
      this.b(var26, var25, 0, 100, (int) (182.0F * (protein / 160000.0F)), 6);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   @Inject(method = "a(II)V",
           at = @At(value = "INVOKE_STRING",
                   target = "endStartSection(Ljava/lang/String;)V",
                   args = "ldc=air",
                   shift = At.Shift.AFTER))
   private void injectRenderOverlayMessage(CallbackInfo c) {
      avi var8 = this.g.l;
      if (this.overlayMsgDisplayTime > 0) {
         this.g.C.startSection("overlayMessage");
         awf window = new awf(this.g.u, this.g.d, this.g.e);
         int var25 = window.a();
         int var26 = window.b();
         GL11.glPushMatrix();
         GL11.glTranslatef((float) (var25 / 2), (float) (var26 - 68), 0.0F);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         var8.b(this.overlayMsg, -var8.a(this.overlayMsg) / 2, -4, this.overlayMsgColor);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
         this.g.C.endSection();
         --this.overlayMsgDisplayTime;
      }
   }

   @Redirect(method = "a(II)V",
           at = @At(value = "INVOKE", target = "Lnet/minecraft/AttributeInstance;getAttributeValue()D"))
   private double redirectHealthLimit(AttributeInstance att) {
      return this.g.h.getHealthLimit();
   }

   public void setOverlayMsg(String overlayMsg, int displayTime, int color) {
      this.overlayMsg = overlayMsg;
      this.overlayMsgColor = color;
      this.overlayMsgDisplayTime = displayTime;
   }

   //   @Inject(method = {"a(FZII)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift = At.Shift.BEFORE)})
//   private void injectRenderPos(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
//      if (Minecraft.inDevMode()) {
//         if (GuiIngame.server_load >= 0) {
//            awf sr = new awf(this.g.u, this.g.d, this.g.e);
//            String text = GuiIngame.server_load + "%";
//            this.b(this.g.l, text, sr.a() - this.g.l.a(text) - 2, 2, 0xE0E0E0);
//         }
//         StringBuilder var69 = new StringBuilder().append("version:").append(Constant.MITE_ITE_VERSION).append("  ").append("Mem: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L + "MB / " + Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB");;
//         if (Minecraft.getErrorMessage() != null) {
//            this.b(this.g.l, var69.toString(), 2, 32, 0xE0E0E0);
//         } else this.b(this.g.l, var69.toString(), 2, 12, 0xE0E0E0);
//      }
//   }
   private String getUIversion() {
      String Version = null;
      String Debug = "";
      try {
         Version = "EXtends-SP " + Constant.MITE_ITE_VERSION + Debug;
      } catch (Error error) {
      }
      if (Minecraft.inDevMode()) {
         Debug = " §4DEV§r";
      }
      if (Version == null) {
         Version = "MITE-EXtends-SP" + Debug;
      }
      return Version;
   }

//   @Overwrite
   public void a(float par1, boolean par2, int par3, int par4) {
      ScoreboardObjective var43;
      int var17;
      int var16;
      int var15;
      int sleep_counter;
      int eye_block_z;
      int eye_block_y;
      int eye_block_x;
      Block block;
      boolean prevent_xray_vision;
      float interpolated_vision_dimming;
      awf var5 = new awf(this.g.u, this.g.d, this.g.e);
      int var6 = var5.a();
      int var7 = var5.b();
      avi var8 = this.g.l;
      this.g.p.c();
      if (this.g.h.isGhost()) {
         return;
      }
      GL11.glEnable(3042);
      int skylight_subtracted = this.g.f.skylightSubtracted;
      int skylight_subtracted_ignoring_rain_and_thunder = this.g.f.skylight_subtracted_ignoring_rain_and_thunder;
      this.g.f.skylightSubtracted = 0;
      this.g.f.skylight_subtracted_ignoring_rain_and_thunder = 0;
      float vignette_brightness = this.g.h.getBrightness(par1);
      this.g.f.skylightSubtracted = skylight_subtracted;
      this.g.f.skylight_subtracted_ignoring_rain_and_thunder = skylight_subtracted_ignoring_rain_and_thunder;
      if (this.g.h.is_cursed && vignette_brightness > 0.0f) {
         vignette_brightness = 0.0f;
      }
      if ((Minecraft.s() || this.g.h.is_cursed) && this.g.u.gui_mode != 2) {
         this.a(vignette_brightness, var6, var7);
      } else {
         GL11.glBlendFunc(770, 771);
      }
      if (this.g.u.aa == 0 && this.g.h.isWearingPumpkinHelmet()) {
         this.b(var6, var7);
      }
      float var10 = 0.0f;
      if (!this.g.h.isPotionActive(MobEffectList.confusion)) {
         var10 = this.g.h.bO + (this.g.h.bN - this.g.h.bO) * par1;
      }
      if ((interpolated_vision_dimming = this.g.h.vision_dimming - par1 * 0.01f) > 0.01f) {
         this.renderVisionDim(var6, var7, Math.min(interpolated_vision_dimming, 0.9f));
      }
      if (this.g.h.runegate_counter > 0) {
         this.renderRunegateEffect(var6, var7);
      }
      boolean bl = prevent_xray_vision = this.g.f.isBlockSolid(block = this.g.f.getBlock(eye_block_x = this.g.h.getBlockPosX(), eye_block_y = MathHelper.floor_double(this.g.h.getEyePosY() - (double) 0.05f), eye_block_z = this.g.h.getBlockPosZ()), eye_block_x, eye_block_y, eye_block_z) && block != Block.glass;
      if (prevent_xray_vision && !block.isOpaqueStandardFormCube(this.g.f, eye_block_x, eye_block_y, eye_block_z)) {
         prevent_xray_vision = false;
      }
      if (prevent_xray_vision) {
         GL11.glDisable(2929);
         GL11.glDisable(3008);
         this.a(0, 0, var6, var7, -16777216);
         GL11.glEnable(3008);
         GL11.glEnable(2929);
      }
      if ((sleep_counter = this.g.h.falling_asleep_counter) > 0) {
         int alpha = Math.min(255 * sleep_counter / 100, 255);
         this.g.C.startSection("sleep");
         GL11.glDisable(2929);
         GL11.glDisable(3008);
         this.a(0, 0, var6, var7, alpha << 24);
         GL11.glEnable(3008);
         GL11.glEnable(2929);
         this.g.C.endSection();
      }
      if (!this.g.c.a() && this.g.u.gui_mode == 0) {
         GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
         if (display_overburdened_cpu_icon_until_ms >= System.currentTimeMillis()) {
            this.g.J().a(MITE_icons);
            this.b(var6 - 17, 2, 0, 0, 16, 16);
         }
         this.g.J().a(c);
         PlayerInventory var31 = this.g.h.inventory;
         this.n = -90.0f;
         this.b(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
         if (this.g.c.auto_use_mode_item != null) {
            GL11.glColor4f(0.8f, 1.0f, 0.7f, 1.0f);
         }
         this.b(var6 / 2 - 91 - 1 + var31.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
         if (this.g.c.auto_use_mode_item != null) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
         }
         this.g.J().a(m);
         GL11.glEnable(3042);
         GL11.glBlendFunc(775, 769);
         this.b(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
         GL11.glDisable(3042);
         this.g.C.startSection("bossHealth");
         this.d();
         this.g.C.endSection();
         if (this.g.c.b()) {
            this.a(var6, var7);
         }
         GL11.glDisable(3042);
         this.g.C.startSection("actionBar");
         GL11.glEnable(32826);
         att.c();
         for (int i = 0; i < 9; ++i) {
            int var12 = var6 / 2 - 90 + i * 20 + 2;
            int var13 = var7 - 16 - 3;
            this.a(i, var12, var13, par1);
         }
         att.a();
         GL11.glDisable(32826);
         this.g.C.endSection();
      }
      int var32 = 0xFFFFFF;
      GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
      int var11 = var6 / 2 - 91;
      if (this.g.h.u() && this.g.u.gui_mode == 0) {
         this.g.C.startSection("jumpBar");
         this.g.J().a(avk.m);
         float var33 = this.g.h.bN();
         int var37 = 182;
         int var14 = (int) (var33 * (float) (var37 + 1));
         var15 = var7 - 32 + 3;
         this.b(var11, var15, 0, 84, var37, 5);
         if (var14 > 0) {
            this.b(var11, var15, 0, 89, var14, 5);
         }
         this.g.C.endSection();
      } else if (this.g.c.f() && this.g.u.gui_mode == 0 && !(this.g.n instanceof axf)) {
         this.g.C.startSection("expBar");
         this.g.J().a(avk.m);
         int var37 = 182;
         int var14 = (int) (this.g.h.getLevelProgress() * (float) (var37 + 1));
         int var152 = var7 - 32 + 3;
         this.b(var11, var152, 0, 64, var37, 5);
         if (var14 > 0) {
            this.b(var11, var152, 0, 69, var14, 5);
         }
         this.g.C.endSection();
         if (this.g.h.getExperienceLevel() != 0 && !(this.g.n instanceof axf)) {
            this.g.C.startSection("expLevel");
            boolean var35 = false;
            int n = var14 = var35 ? 0xFFFFFF : 8453920;
            if (this.g.h.getExperienceLevel() < 0) {
               var14 = 0xFF1313;
            }
            String var42 = "" + this.g.h.getExperienceLevel();
            var16 = (var6 - var8.a(var42)) / 2;
            var17 = var7 - 31 - 4;
            boolean var18 = false;
            var8.b(var42, var16 + 1, var17, 0);
            var8.b(var42, var16 - 1, var17, 0);
            var8.b(var42, var16, var17 + 1, 0);
            var8.b(var42, var16, var17 - 1, 0);
            var8.b(var42, var16, var17, var14);
            this.g.C.endSection();
         }
      }
      if (this.curse_notification_counter > 0 && this.g.h.is_cursed) {
         int alpha;
         avi fr = this.g.l;
         awf sr = new awf(this.g.u, this.g.d, this.g.e);
         String text = Translator.get(this.g.h.is_cursed ? "curse.notify" : "curse.lifted");
         int var14 = var7 - 59;
         if (!this.g.c.b()) {
            var14 += 14;
         }
         if ((alpha = (int) ((float) this.curse_notification_counter * 256.0f / 10.0f)) > 255) {
            alpha = 255;
         }
         if (alpha > 0) {
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            fr.a(EnumChatFormat.DARK_PURPLE + text, (sr.a() - fr.a(text)) / 2, var14, 0xFFFFFF + (alpha << 24));
            GL11.glDisable(3042);
         }
         this.q = 0;
      }
      if (this.g.u.D && this.g.u.gui_mode == 0) {
         this.g.C.startSection("toolHighlight");
         if (this.q > 0 && this.r != null) {
            String var36 = this.r.getMITEStyleDisplayName();
            int var13 = (var6 - var8.a(var36)) / 2;
            int var14 = var7 - 59;
            if (!this.g.c.b()) {
               var14 += 14;
            }
            if ((var15 = (int) ((float) this.q * 256.0f / 10.0f)) > 255) {
               var15 = 255;
            }
            if (var15 > 0) {
               GL11.glPushMatrix();
               GL11.glEnable(3042);
               GL11.glBlendFunc(770, 771);
               var8.a(var36, var13, var14, 0xFFFFFF + (var15 << 24));
               GL11.glDisable(3042);
               GL11.glPopMatrix();
            }
            this.last_highlighting_item_stack_text = var36;
         }
         if (this.r == null) {
            this.last_highlighting_item_stack_text = null;
         }
         this.g.C.endSection();
      }
      if (this.g.p()) {
         this.g.C.startSection("demo");
         String var36 = "";
         var36 = this.g.f.getTotalWorldTime() >= 120500L ? bkb.a("demo.demoExpired") : bkb.a("demo.remainingTime", new Object[]{StripColor.a((int) (120500L - this.g.f.getTotalWorldTime()))});
         int var13 = var8.a(var36);
         var8.a(var36, var6 - var13 - 10, 5, 0xFFFFFF);
         this.g.C.endSection();
      }
      if (DedicatedServer.tournament_type == EnumTournamentType.score) {
         this.g.h.delta_tournament_score_opacity -= 2;
         if (this.g.h.delta_tournament_score_opacity < 0) {
            this.g.h.delta_tournament_score = 0;
            this.g.h.delta_tournament_score_opacity = 0;
         }
         this.g.last_known_delta_tournament_score = this.g.h.delta_tournament_score;
         this.g.last_known_delta_tournament_score_opacity = this.g.h.delta_tournament_score_opacity;
         this.g.last_known_tournament_score = this.g.h.tournament_score;
      }
      int row = 0;
      if (Minecraft.getErrorMessage() != null) {
         this.b(var8, Minecraft.getErrorMessage(), 2, 2 + 10 * row++, 0xFF1313);
         this.b(var8, "Press [c] to clear error message.", 2, 2 + 10 * row++, 0xFF1313);
      }
      if (this.g.u.ab && this.g.u.gui_mode == 0) {
         if (DedicatedServer.tournament_type == EnumTournamentType.score) {
            this.drawTournamentScore(row++, 2, var8);
         }
         if (allotted_time >= 0) {
            this.drawAllottedTime(row++, 2, var8);
         }
      }
      if (this.g.u.gui_mode == 0) {
         String Weather;
         int ran2;
         String FACE;
         switch (this.g.h.getDirectionFromYaw().toString()) {
            case "EAST": {
               FACE = "东";
               break;
            }
            case "WEST": {
               FACE = "西";
               break;
            }
            case "NORTH": {
               FACE = "北";
               break;
            }
            case "SOUTH": {
               FACE = "南";
               break;
            }
            default: {
               FACE = "null";
            }
         }
         String Biome = StringUtils.substringBefore(this.g.h.getBiome().toString(), "@").substring(19) + " ";
         String RainSnow = Biome.equals("Taiga") || Biome.equals("TaigaHills") ? "雪" : "雨";
         WeatherEvent event = this.g.f.getCurrentWeatherEvent();
         Random R = new Random(this.g.f.getDayOfWorld());
         if (event != null) {
            String START = event.start / 24000L + 1L + "天" + (event.start % 24000L / 1000L + 6L) + "时";
            String END = event.end / 24000L + 1L + "天" + (event.end % 24000L / 1000L + 6L) + "时";
            Weather = this.g.f.getDayOfWorld() % 32 == 0 ? "雷暴 " + START + " - " + END : ((ran2 = R.nextInt(3)) == 0 ? "小" + RainSnow + " " + START + " - " + END : (ran2 == 1 ? "中" + RainSnow + " " + START + " - " + END : "大" + RainSnow + " " + START + " - " + END));
         } else {
            event = this.g.f.getNextWeatherEvent(false);
            Weather = event != null ? (event.start - (long) this.g.f.getAdjustedTimeOfDay() < 6000L ? "有雨" : "阴") : ((ran2 = R.nextInt(3)) == 0 ? "晴" : (ran2 == 1 ? "多云" : "晴转多云"));
         }
         int x = MathHelper.floor_double(this.g.h.posX);
         int y = MathHelper.floor_double(this.g.h.posY - (double) this.g.h.yOffset);
         int z = MathHelper.floor_double(this.g.h.posZ);
         String Light = " " + this.g.h.getWorld().getBlockLightValue(x, y, z) + " ";
         String Version2 = this.getUIversion();
         String POS = " (" + MathHelper.floor_double(this.g.h.posX) + ", " + MathHelper.floor_double(this.g.h.posY - (double) this.g.h.yOffset) + ", " + MathHelper.floor_double(this.g.h.posZ) + ") ";
         String DayTime = this.g.h.getWorld().getDayOfWorld() + "天" + this.g.h.getWorld().getHourOfDay() + "点";
         String Body = " (" + this.g.h.getSatiation() + "-" + this.g.h.getNutrition() + ") ";
         String FPS = "" + Minecraft.last_fps;
         StringBuilder var68 = new StringBuilder().append(Version2 + " ").append(FACE + " ").append(POS).append(Light).append(Biome);
         var68.append(DayTime).append(Body).append(Weather);
         this.b(var8, var68.toString(), 2, 2 + 10 * row++, 0xE0E0E0);
         StringBuilder var69 = new StringBuilder().append(FPS);
         this.b(var8, var69.toString(), 2, 2 + 10 * row++, 0xE0E0E0);
         if (Minecraft.inDevMode()) {
            if (server_load >= 0) {
               awf sr = new awf(this.g.u, this.g.d, this.g.e);
               String text = server_load + "%";
               this.b(var8, text, sr.a() - var8.a(text) - 2, 2, 0xE0E0E0);
            }
            if (Debug.flag) {
               this.b(var8, "FLAG", 320, 2, 0xFF1313);
            }
         }
      }
//      if (this.g.u.gui_mode == 0) {
//         ItemStack item_stack = this.currentItem < 9 && this.currentItem >= 0 ? this.mainInventory[this.currentItem] : null;
//         String durability ="手持物品耐久=" +  item_stack.getRemainingDurability() + "/" + item_stack.getItem().getMaxDamage(item_stack);
//         StringBuilder var70 = new StringBuilder().append(durability);
//         this.b(var8, var70.toString(), 2, 96, 0xE0E0E0);
//      }
      if (Debug.is_active && this.g.u.gui_mode == 0) {
         this.b(var8, "Counter: " + Debug.general_counter, 2, 2 + 10 * row++, 0xE0E0E0);
         if (Debug.biome_info != null) {
            this.b(var8, Debug.biome_info, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         if (Debug.selected_object_info != null) {
            this.b(var8, Debug.selected_object_info, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         if (Debug.equipped_item_info != null) {
            this.b(var8, Debug.equipped_item_info, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         if (Debug.general_info != null) {
            this.b(var8, Debug.general_info, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         if (Debug.general_info_client != null) {
            this.b(var8, "[Client] " + Debug.general_info_client, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         if (Debug.general_info_server != null) {
            this.b(var8, "[Server] " + Debug.general_info_server, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         row += 2;
         WeatherEvent event = this.g.f.getCurrentWeatherEvent();
         String s = event != null ? "Current rain: " + event.start + " to " + event.end : ((event = this.g.f.getNextWeatherEvent(false)) != null ? "Next rain: " + event.start + " to " + event.end : ((event = this.g.f.getPreviousWeatherEvent(false)) == null ? "No rain today" : "Previous rain: " + event.start + " to " + event.end));
         this.b(var8, s, 2, 2 + 10 * row++, 0xE0E0E0);
         event = this.g.f.getCurrentWeatherEvent(true, false);
         s = event != null ? "Current storm: " + event.start_of_storm + " to " + event.end_of_storm : ((event = this.g.f.getNextWeatherEvent(true)) != null ? "Next storm: " + event.start_of_storm + " to " + event.end_of_storm : ((event = this.g.f.getPreviousWeatherEvent(true)) == null ? "No storm today" : "Previous storm: " + event.start_of_storm + " to " + event.end_of_storm));
         this.b(var8, s, 2, 2 + 10 * row++, 0xE0E0E0);
         this.b(var8, "Client Pools: " + AxisAlignedBB.getAABBPool().getlistAABBsize() + " | " + this.g.f.getWorldVec3Pool().getPoolSize(), 2, 2 + 10 * row++, 0xE0E0E0);
         this.b(var8, "Server Pools: " + Minecraft.server_pools_string, 2, 2 + 10 * row++, 0xE0E0E0);
         int n = ++row;
         this.b(var8, "Atk: " + StringHelper.formatFloat(this.g.h.calcRawMeleeDamageVs(null), 1, 1) + "  Prt:" + StringHelper.formatFloat(this.g.h.getTotalProtection(null), 1, 1), 2, 2 + 10 * n, 0xE0E0E0);
         int n2 = ++row;
         this.b(var8, "Look: " + MathHelper.getNormalizedVector(this.g.h.rotationYaw, this.g.h.rotationPitch, this.g.f.getWorldVec3Pool()).toStringCompact(), 2, 2 + 10 * n2, 0xE0E0E0);
         int n3 = ++row;
         this.b(var8, "fxLayers" + this.g.k.getStatsString(), 2, 2 + 10 * n3, 0xE0E0E0);
         Chunk chunk = this.g.h.getChunkFromPosition();
         int n4 = ++row;
         ++row;
         this.b(var8, "Chunk: " + chunk.xPosition + "," + chunk.zPosition + " [" + (this.g.h.getFootBlockPosY() >> 4) + "] FPP=" + StringHelper.formatDouble(EntityRenderer.getProximityToNearestFogPost(this.g.h), 3, 3), 2, 2 + 10 * n4, 0xE0E0E0);
         MinecraftServer mc_server = MinecraftServer.F();
         if (mc_server != null) {
            WorldServer world_server = mc_server.a(this.g.h.dimension);
            this.b(var8, "Mobs high: " + world_server.countMobs(false, true) + " / " + world_server.last_mob_spawn_limit_at_60_or_higher, 2, 2 + 10 * row++, 0xE0E0E0);
            this.b(var8, "Mobs low:  " + world_server.countMobs(true, false) + " / " + world_server.last_mob_spawn_limit_under_60, 2, 2 + 10 * row++, 0xE0E0E0);
         }
         this.b(var8, "Mem: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L + " / " + Runtime.getRuntime().totalMemory() / 1024L / 1024L, 2, 2 + 10 * row++, 0xE0E0E0);
      } else if (this.g.u.ab && this.g.u.gui_mode == 0) {
         this.g.C.startSection("debug");
         this.g.C.endSection();
         if (DedicatedServer.tournament_type != EnumTournamentType.score && allotted_time < 0) {
            this.b(var8, "" + Minecraft.last_fps, 2, 2 + 10 * row++, 0xE0E0E0);
         }
      }

         if (this.o > 0) {
            this.g.C.startSection("overlayMessage");
            float var33 = (float) this.o - par1;
            int var13 = (int) (var33 * 255.0f / 20.0f);
            if (var13 > 255) {
               var13 = 255;
            }
            if (var13 > 8) {
               GL11.glPushMatrix();
               GL11.glTranslatef(var6 / 2, var7 - 68, 0.0f);
               GL11.glEnable(3042);
               GL11.glBlendFunc(770, 771);
               int var14 = 0xFFFFFF;
               if (this.p) {
                  var14 = Color.HSBtoRGB(var33 / 50.0f, 0.7f, 0.6f) & 0xFFFFFF;
               }
               var8.b(this.j, -var8.a(this.j) / 2, -4, var14 + (var13 << 24 & 0xFF000000));
               GL11.glDisable(3042);
               GL11.glPopMatrix();
            }
            this.g.C.endSection();
         }
         if ((var43 = this.g.f.getScoreboard().func_96539_a(1)) != null) {
            this.a(var43, var7, var6, var8);
         }
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(3008);
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0f, var7 - 48, 0.0f);
         this.g.C.startSection("chat");
         this.h.a(this.i);
         this.g.C.endSection();
         GL11.glPopMatrix();
         var43 = this.g.f.getScoreboard().func_96539_a(0);
         if (this.g.u.T.e && (!this.g.A() || this.g.h.netClientHandler.c.size() > 1 || var43 != null)) {
            int var153;
            this.g.C.startSection("playerList");
            NetClientHandler var41 = this.g.h.netClientHandler;
            List var44 = var41.c;
            var16 = var153 = var41.d;
            var17 = 1;
            while (var16 > 20) {
               var16 = (var153 + ++var17 - 1) / var17;
            }
            int var45 = 300 / var17;
            if (var45 > 150) {
               var45 = 150;
            }
            int var19 = (var6 - var17 * var45) / 2;
            int var47 = 10;
            this.a(var19 - 1, var47 - 1, var19 + var45 * var17, var47 + 9 * var16, Integer.MIN_VALUE);
            int players_skipped = 0;
            for (int var21 = 0; var21 < var153; ++var21) {
               int var27;
               int var28;
               int var22 = var19 + var21 % var17 * var45;
               int var23 = var47 + var21 / var17 * 9;
               int var23_alt = var47 + (var21 - players_skipped) / var17 * 9;
               this.a(var22, var23, var22 + var45 - 1, var23 + 8, 0x20FFFFFF);
               GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
               GL11.glEnable(3008);
               if (var21 >= var44.size()) continue;
               bdj var49 = (bdj) var44.get(var21);
               ScoreboardTeam var48 = this.g.f.getScoreboard().getPlayersTeam(var49.a);
               String var52 = ScoreboardTeam.formatPlayerName(var48, var49.a);
               if ("avernite".equals(var52) && DedicatedServer.isTournament()) {
                  ++players_skipped;
                  continue;
               }
               var8.a(var52, var22, var23_alt, 0xFFFFFF);
               if (var43 != null) {
                  var28 = var22 + var45 - 12 - 5;
                  var27 = var22 + var8.a(var52) + 5;
                  if (var28 - var27 > 5) {
                     ScoreboardScore var29 = var43.a().func_96529_a(var49.a, var43);
                     String var30 = EnumChatFormat.YELLOW + "" + var29.getScorePoints();
                     var8.a(var30, var28 - var8.a(var30), var23_alt, 0xFFFFFF);
                  }
               } else {
                  var28 = var22 + var45 - 12 - 5;
                  var27 = var22 + var8.a(var52) + 5;
                  if (var28 - var27 > 5) {
                     String level = var49.level < 0 ? EnumChatFormat.RED + "" + var49.level : (var49.level == 0 ? EnumChatFormat.GRAY + "" + var49.level : EnumChatFormat.GREEN + "+" + var49.level);
                     var8.a(level, var28 - var8.a(level), var23_alt, 0xFFFFFF);
                  }
               }
               GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
               this.g.J().a(m);
               int var53 = 0;
               boolean var51 = false;
               int var50 = var49.b < 0 ? 5 : (var49.b < 150 ? 0 : (var49.b < 300 ? 1 : (var49.b < 600 ? 2 : (var49.b < 1000 ? 3 : 4))));
               this.n += 100.0f;
               this.b(var22 + var45 - 12, var23_alt, 0 + var53 * 10, 176 + var50 * 8, 10, 8);
               this.n -= 100.0f;
            }
         }
         GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
         GL11.glDisable(2896);
         GL11.glEnable(3008);
      }


   @Shadow
   private void d() {
   }

   @Shadow
   private void a(ScoreboardObjective par1ScoreObjective, int par2, int par3, avi par4FontRenderer) {
   }

   @Shadow
   private void drawAllottedTime(int i, int i1, avi var8) {
   }

   @Shadow
   private void a(int var11, int var12, int var13, float par1) {
   }

   @Shadow
   private void renderRunegateEffect(int var6, int var7) {
   }

   @Shadow
   private void renderVisionDim(int var6, int var7, float min) {
   }

   @Shadow
   private void a(float vignette_brightness, int var6, int var7) {
   }

   @Shadow
   private void b(int var6, int var7) {
   }

   @Shadow
   private void drawTournamentScore(int i, int i1, avi var8) {
   }

   @Shadow
   private void a(int var6, int var7) {
   }

//   @Shadow
//   public void b(int par1, int par2, int par3, int par4, int par5, int par6) {
//   }
}
