
package net.mcreator.klbsnewpit.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.klbsnewpit.KlbsNewPitModElements;

@KlbsNewPitModElements.ModElement.Tag
public class VampireEnchantmentEnchantment extends KlbsNewPitModElements.ModElement {
	@ObjectHolder("klbs_new_pit:vampire_enchantment")
	public static final Enchantment enchantment = null;

	public VampireEnchantmentEnchantment(KlbsNewPitModElements instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("vampire_enchantment"));
	}

	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_LEGS, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 3;
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
			return false;
		}
	}
}
