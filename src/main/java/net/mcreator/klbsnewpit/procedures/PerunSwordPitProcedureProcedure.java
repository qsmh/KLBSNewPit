package net.mcreator.klbsnewpit.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.klbsnewpit.item.DarkPantArmorItem;
import net.mcreator.klbsnewpit.KlbsNewPitModVariables;
import net.mcreator.klbsnewpit.KlbsNewPitMod;

import java.util.Map;

public class PerunSwordPitProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency world for procedure PerunSwordPitProcedure!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency x for procedure PerunSwordPitProcedure!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency y for procedure PerunSwordPitProcedure!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency z for procedure PerunSwordPitProcedure!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency entity for procedure PerunSwordPitProcedure!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency sourceentity for procedure PerunSwordPitProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
				.getItem() == DarkPantArmorItem.legs) == false) {
			if ((sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new KlbsNewPitModVariables.PlayerVariables())).PerunStrikes == 3
					&& (sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new KlbsNewPitModVariables.PlayerVariables())).PerunDebounce == false) {
				{
					boolean _setval = (true);
					sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.PerunDebounce = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				if (world instanceof ServerWorld) {
					LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
					_ent.moveForced(Vector3d.copyCenteredHorizontally(new BlockPos(x, y, z)));
					_ent.setEffectOnly(true);
					((World) world).addEntity(_ent);
				}
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
				{
					double _setval = 0;
					sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.PerunStrikes = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						{
							boolean _setval = (false);
							sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.PerunDebounce = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 9);
			} else if ((sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new KlbsNewPitModVariables.PlayerVariables())).PerunStrikes != 3
					&& (sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new KlbsNewPitModVariables.PlayerVariables())).PerunDebounce == false) {
				{
					boolean _setval = (true);
					sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.PerunDebounce = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				{
					double _setval = ((sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new KlbsNewPitModVariables.PlayerVariables())).PerunStrikes + 1);
					sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.PerunStrikes = _setval;
						capability.syncPlayerVariables(sourceentity);
					});
				}
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						{
							boolean _setval = (false);
							sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.PerunDebounce = _setval;
								capability.syncPlayerVariables(sourceentity);
							});
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 9);
			}
		}
	}
}
