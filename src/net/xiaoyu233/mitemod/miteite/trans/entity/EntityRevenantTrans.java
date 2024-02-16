package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.xiaoyu233.mitemod.miteite.util.Constant;
import net.xiaoyu233.mitemod.miteite.util.MonsterUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(EntityRevenant.class)
public class EntityRevenantTrans extends EntityZombie {
   public EntityRevenantTrans(World world) {
      super(world);
   }

   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
      super.dropFewItems(recently_hit_by_player, damage_source);
      if (recently_hit_by_player) {
         int day = this.getWorld().getDayOfOverworld();
         if (day >= 128) {
            this.dropItemStack(new ItemStack(Items.dyePowder, 1, 4));
         }
      }
   }

   public void addRandomWeapon() {
      List items = new ArrayList();
      items.add(new RandomItemListEntry(Item.swordRustedIron, 2));
      items.add(new RandomItemListEntry(Items.clubIron, 2));
      if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
         items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
      }

      if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
         items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1));
      }

      RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
      this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
   }

   @Overwrite
   protected void addRandomEquipment() {
      this.addRandomWeapon();
      int day = this.getWorld().getDayOfOverworld();
      if (day < 128) {
         this.setBoots((new ItemStack(Item.bootsRustedIron)).randomizeForMob(this, true));
         this.setLeggings((new ItemStack(Item.legsRustedIron)).randomizeForMob(this, true));
         this.setCuirass((new ItemStack(Item.plateRustedIron)).randomizeForMob(this, true));
         this.setHelmet((new ItemStack(Item.helmetRustedIron)).randomizeForMob(this, true));
      } else {
         MonsterUtil.addDefaultArmor(day, this, true);
      }
   }

   @Overwrite
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      int day = this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0;
      this.setEntityAttribute(GenericAttributes.followRange, 64.0D);
      this.setEntityAttribute(GenericAttributes.movementSpeed, 0.2800000011920929D);
      this.setEntityAttribute(GenericAttributes.attackDamage, 12.0D + day / 12d);
      this.setEntityAttribute(EntityZombie.field_110186_bp, this.getRNG().nextDouble() * 0.10000000149011612D);
      this.setEntityAttribute(GenericAttributes.maxHealth, 35.0D + day / 8D);
   }

   protected void enchantEquipment(ItemStack item_stack) {
      if ((double)this.getRNG().nextFloat() <= 0.2D + (double)this.getWorld().getDayOfOverworld() / 64.0D / 10.0D) {
         EnchantmentManager.addRandomEnchantment(this.getRNG(), item_stack, (int)(5.0F + (float)((this.getRNG().nextInt(15 + this.getWorld().getDayOfOverworld() / 24) + 3) / 10) * (float)this.getRNG().nextInt(18)));
      }
   }
}
