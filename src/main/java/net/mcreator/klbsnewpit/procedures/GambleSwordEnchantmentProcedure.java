package net.mcreator.klbsnewpit.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.klbsnewpit.item.DarkPantArmorItem;
import net.mcreator.klbsnewpit.enchantment.MirrorEnchantmentEnchantment;
import net.mcreator.klbsnewpit.enchantment.GambleEnchantmentEnchantment;
import net.mcreator.klbsnewpit.KlbsNewPitModVariables;
import net.mcreator.klbsnewpit.KlbsNewPitMod;

import java.util.Map;
import java.util.HashMap;

public class GambleSwordEnchantmentProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingHurtEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
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
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency world for procedure GambleSwordEnchantment!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency x for procedure GambleSwordEnchantment!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency y for procedure GambleSwordEnchantment!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency z for procedure GambleSwordEnchantment!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency entity for procedure GambleSwordEnchantment!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				KlbsNewPitMod.LOGGER.warn("Failed to load dependency sourceentity for procedure GambleSwordEnchantment!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((EnchantmentHelper.getEnchantmentLevel(GambleEnchantmentEnchantment.enchantment,
				((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0)) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
					.getItem() == DarkPantArmorItem.legs) == false) {
				if ((sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new KlbsNewPitModVariables.PlayerVariables())).GambleDebounce == false) {
					{
						boolean _setval = (true);
						sourceentity.getCapability(KlbsNewPitModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.GambleDebounce = _setval;
							capability.syncPlayerVariables(sourceentity);
						});
					}
					if (EnchantmentHelper.getEnchantmentLevel(GambleEnchantmentEnchantment.enchantment,
							((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) == 1) {
						if (Math.random() <= 0.4) {
							sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2);
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
						} else {
							if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 1) {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 0);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 2) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 3) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
							}
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
										capability.GambleDebounce = _setval;
										capability.syncPlayerVariables(sourceentity);
									});
								}
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, (int) 9);
					}
					if (EnchantmentHelper.getEnchantmentLevel(GambleEnchantmentEnchantment.enchantment,
							((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) == 2) {
						if (Math.random() <= 0.4) {
							sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
						} else {
							if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 1) {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 0);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 2) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 3) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
							}
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
										capability.GambleDebounce = _setval;
										capability.syncPlayerVariables(sourceentity);
									});
								}
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, (int) 9);
					}
					if (EnchantmentHelper.getEnchantmentLevel(GambleEnchantmentEnchantment.enchantment,
							((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) == 3) {
						if (Math.random() <= 0.4) {
							sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 6);
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.anvil.place")),
												SoundCategory.MASTER, (float) 1, (float) (-1));
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.anvil.place")),
										SoundCategory.MASTER, (float) 1, (float) (-1), false);
							}
						} else {
							if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 1) {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 0);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 2) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else if ((EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
									((entity instanceof LivingEntity)
											? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
											: ItemStack.EMPTY)) != 0)
									&& EnchantmentHelper.getEnchantmentLevel(MirrorEnchantmentEnchantment.enchantment,
											((entity instanceof LivingEntity)
													? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS)
													: ItemStack.EMPTY)) == 3) {
								sourceentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 4);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1));
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.anvil.place")),
											SoundCategory.MASTER, (float) 1, (float) (-1), false);
								}
							} else {
								entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 6);
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
								if (world instanceof World && !world.isRemote()) {
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("entity.arrow.hit_player")),
											SoundCategory.MASTER, (float) 1, (float) 1, false);
								}
							}
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
										capability.GambleDebounce = _setval;
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
	}
}
