package net.mcreator.klbsnewpit.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.klbsnewpit.enchantment.PhoenixEnchantmentEnchantment;
import net.mcreator.klbsnewpit.KlbsNewPitModVariables;
import net.mcreator.klbsnewpit.KlbsNewPitMod;

import java.util.Map;
import java.util.HashMap;

public class PhoenixPantEnchantmentProcedure {
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
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency world for procedure PhoenixPantEnchantment!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency x for procedure PhoenixPantEnchantment!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency y for procedure PhoenixPantEnchantment!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency z for procedure PhoenixPantEnchantment!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency entity for procedure PhoenixPantEnchantment!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof ServerPlayerEntity || entity instanceof PlayerEntity) {
			if ((EnchantmentHelper.getEnchantmentLevel(PhoenixEnchantmentEnchantment.enchantment,
					((entity instanceof LivingEntity)
							? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
							: ItemStack.EMPTY)) != 0)) {
				if ((entity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new KlbsNewPitModVariables.PlayerVariables())).PhoenixDebounce == false) {
					{
						boolean _setval = (true);
						entity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PhoenixDebounce = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (dependencies.get("event") != null) {
						Object _obj = dependencies.get("event");
						if (_obj instanceof Event) {
							Event _evt = (Event) _obj;
							if (_evt.isCancelable())
								_evt.setCanceled(true);
						}
					}
					if (EnchantmentHelper.getEnchantmentLevel(PhoenixEnchantmentEnchantment.enchantment,
							((entity instanceof LivingEntity)
									? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
									: ItemStack.EMPTY)) == 1) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity)
									.setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos(x, y, z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6));
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6), false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LAVA, x, y, z, (int) 55, 0.5, 0.5, 0.5, 0.1);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, x, y, z, (int) 40, 0.5, 0.5, 0.5, 0.1);
						}
					}
					if (EnchantmentHelper.getEnchantmentLevel(PhoenixEnchantmentEnchantment.enchantment,
							((entity instanceof LivingEntity)
									? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
									: ItemStack.EMPTY)) == 2) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity)
									.setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 100, (int) 0));
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos(x, y, z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6));
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6), false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LAVA, x, y, z, (int) 55, 0.5, 0.5, 0.5, 0.1);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, x, y, z, (int) 40, 0.5, 0.5, 0.5, 0.1);
						}
					}
					if (EnchantmentHelper.getEnchantmentLevel(PhoenixEnchantmentEnchantment.enchantment,
							((entity instanceof LivingEntity)
									? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
									: ItemStack.EMPTY)) == 3) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity)
									.setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 300, (int) 1));
						if (entity instanceof LivingEntity)
							((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 800, (int) 0));
						if (world instanceof World && !world.isRemote()) {
							((World) world).playSound(null, new BlockPos(x, y, z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6));
						} else {
							((World) world).playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.bat.death")),
									SoundCategory.MASTER, (float) 2, (float) (-6), false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LAVA, x, y, z, (int) 55, 0.5, 0.5, 0.5, 0.1);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, x, y, z, (int) 40, 0.5, 0.5, 0.5, 0.1);
						}
					}
				}
			}
		}
	}
}
