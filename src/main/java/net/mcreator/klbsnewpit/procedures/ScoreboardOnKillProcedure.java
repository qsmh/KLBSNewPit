package net.mcreator.klbsnewpit.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.klbsnewpit.KlbsNewPitModVariables;
import net.mcreator.klbsnewpit.KlbsNewPitMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class ScoreboardOnKillProcedure {
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
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency sourceentity for procedure ScoreboardOnKill!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (sourceentity instanceof PlayerEntity) {
			{
				double _setval = ((sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new KlbsNewPitModVariables.PlayerVariables())).PlayerGold + MathHelper.nextInt(new Random(), 1, 10));
				sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.PlayerGold = _setval;
					capability.syncPlayerVariables(sourceentity);
				});
			}
		}
	}
}
