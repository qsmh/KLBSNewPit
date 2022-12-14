
package net.mcreator.klbsnewpit.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.klbsnewpit.KlbsNewPitModElements;

@KlbsNewPitModElements.ModElement.Tag
public class EvilWithinEnchantmentEnchantment extends KlbsNewPitModElements.ModElement {
	@ObjectHolder("klbs_new_pit:evil_within_enchantment")
	public static final Enchantment enchantment = null;

	public EvilWithinEnchantmentEnchantment(KlbsNewPitModElements instance) {
		super(instance, 16);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("evil_within_enchantment"));
	}

	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR_LEGS, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		public int calcModifierDamage(int level, DamageSource source) {
			return level * 5;
		}

		@Override
		protected boolean canApplyTogether(Enchantment ench) {
			if (ench == Enchantments.THORNS)
				return true;
			if (ench == Enchantments.FIRE_ASPECT)
				return true;
			return false;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == Items.LEATHER_LEGGINGS)
				return true;
			if (stack.getItem() == Items.WOODEN_SWORD)
				return true;
			if (stack.getItem() == Items.STONE_SWORD)
				return true;
			if (stack.getItem() == Items.IRON_SWORD)
				return true;
			if (stack.getItem() == Items.GOLDEN_SWORD)
				return true;
			if (stack.getItem() == Items.DIAMOND_SWORD)
				return true;
			if (stack.getItem() == Items.NETHERITE_SWORD)
				return true;
			return false;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}

		@Override
		public boolean canGenerateInLoot() {
			return true;
		}

		@Override
		public boolean canVillagerTrade() {
			return true;
		}
	}
}
