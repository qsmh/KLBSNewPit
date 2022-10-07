package net.mcreator.klbsnewpit.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.klbsnewpit.enchantment.VampireEnchantmentEnchantment;
import net.mcreator.klbsnewpit.KlbsNewPitMod;

import java.util.Map;
import java.util.HashMap;

public class VampirePantEnchantmentProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityDeath(LivingDeathEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency sourceentity for procedure VampirePantEnchantment!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((EnchantmentHelper.getEnchantmentLevel(VampireEnchantmentEnchantment.enchantment,
				((sourceentity instanceof LivingEntity)
						? ((LivingEntity) sourceentity).getItemStackFromSlot(EquipmentSlotType.LEGS)
						: ItemStack.EMPTY)) != 0)) {
			if (EnchantmentHelper.getEnchantmentLevel(VampireEnchantmentEnchantment.enchantment,
					((sourceentity instanceof LivingEntity)
							? ((LivingEntity) sourceentity).getItemStackFromSlot(EquipmentSlotType.LEGS)
							: ItemStack.EMPTY)) == 1) {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 100, (int) 0));
			}
			if (EnchantmentHelper.getEnchantmentLevel(VampireEnchantmentEnchantment.enchantment,
					((sourceentity instanceof LivingEntity)
							? ((LivingEntity) sourceentity).getItemStackFromSlot(EquipmentSlotType.LEGS)
							: ItemStack.EMPTY)) == 2) {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 200, (int) 0));
			}
			if (EnchantmentHelper.getEnchantmentLevel(VampireEnchantmentEnchantment.enchantment,
					((sourceentity instanceof LivingEntity)
							? ((LivingEntity) sourceentity).getItemStackFromSlot(EquipmentSlotType.LEGS)
							: ItemStack.EMPTY)) == 3) {
				if (sourceentity instanceof LivingEntity)
					((LivingEntity) sourceentity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 300, (int) 0));
			}
		}
	}
}
