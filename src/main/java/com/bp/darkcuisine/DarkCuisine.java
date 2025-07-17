package com.bp.darkcuisine;

import com.bp.darkcuisine.ItemGroup.DarkCuisineItemGroup;
import com.bp.darkcuisine.effect.FrogEffect;
import com.bp.darkcuisine.effect.WeiEffect;
import com.bp.darkcuisine.entity.MobEntities;
import com.bp.darkcuisine.entity.client.GrabHandler;
import com.bp.darkcuisine.entity.client.GrabPayload;
import com.bp.darkcuisine.entity.custom.tsteEntity;
import com.bp.darkcuisine.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static com.bp.darkcuisine.effect.FrogEffect.*;
import static com.bp.darkcuisine.entity.MobEntities.mosquito;
import static net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.registerGlobalReceiver;
import static net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents.START_SLEEPING;

public class DarkCuisine implements ModInitializer {
	public static final String MOD_ID = "dark-cuisine";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public  final EntityType<tsteEntity> mosquitoCopy=MobEntities.mosquito;
	public static KeyBinding GRAB_KEY;
	//public static final KeyBinding GRAB_KEY = KeyBindingHelper.registerKeyBinding(
			//new KeyBinding("key.dark-cuisine.grab", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "category.dark-cuisine")
	//);

	public static final RegistryEntry<StatusEffect> fg = Registry.registerReference(Registries.STATUS_EFFECT,Identifier.of(DarkCuisine.MOD_ID,"frog-e"),new FrogEffect());
	public static final RegistryEntry<StatusEffect> wei = Registry.registerReference(Registries.STATUS_EFFECT,Identifier.of(DarkCuisine.MOD_ID,"wei"),new WeiEffect());
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		PayloadTypeRegistry.playC2S().register(
				GrabPayload.ID,
				GrabPayload.CODEC
		);

		// 注册按键绑定
		GRAB_KEY = new KeyBinding(
				"key." + MOD_ID + ".grab",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_R,
				"category." + MOD_ID
		);
		KeyBindingHelper.registerKeyBinding(GRAB_KEY);

		// 注册实体
		MobEntities.register();

		// 注册服务端处理器
		GrabHandler.registerServerPacket();
		//ClientTickEvents.END_CLIENT_TICK.register(new ClientInputHandler());
		// 服务端逻辑注册
		ModItems.regModItems();
		DarkCuisineItemGroup.initialize();

		Registry.register(Registries.SOUND_EVENT, Identifier.of(DarkCuisine.MOD_ID,"mos"), SoundEvent.of(Identifier.of(DarkCuisine.MOD_ID,"mos")));



		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(mosquito, tsteEntity.createAttributes());
		//???为什么这行代码会让下方mosquito总是null啊

			SpawnRestriction.register(mosquitoCopy, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, tsteEntity::canMobSpawn);

			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (0.45f <= biomeSelectionContext.getBiome().getTemperature() && biomeSelectionContext.getBiome().getTemperature() <= 0.75f) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 15, 1, 2);
			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (0.75f <= biomeSelectionContext.getBiome().getTemperature() && biomeSelectionContext.getBiome().getTemperature() <= 1.2f) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 25, 1, 3);
			BiomeModifications.addSpawn(biomeSelectionContext -> {
				if (1.2f <= biomeSelectionContext.getBiome().getTemperature()) {
					return true;
				} else {
					return false;
				}
			}, SpawnGroup.CREATURE, mosquitoCopy, 40, 2, 3);

		START_SLEEPING.register((player, bedPos) -> {
			World world = player.getWorld();
			if (world.isClient) return;

			if(!player.isPlayer()) return;
			// 检查维度（仅主世界）
			//if (world.getRegistryKey() != World.OVERWORLD) return;
			var wendu =world.getBiome(bedPos).value().getTemperature();
			if(wendu<=0.5f) return;
			// 检查时间（夜晚或雷暴）
			long time = world.getTimeOfDay() % 24000;
			//boolean isNight = time >= 12541 && time <= 23458;
			//boolean isThundering = world.isThundering();
			//if (!isNight && !isThundering) return;
			if(new Random().nextFloat()>(wendu-0.5f)/3f) return;
			// 检查周围是否有怪物（简化版）
			//if (!isSafeAroundBed((PlayerEntity) player, bedPos)) return;


			Vec3d pos = player.getPos();
			Vec3d lookVec = player.getRotationVector().multiply(2.0); // 生成在玩家前方
			Vec3d spawnPos = pos.add(lookVec);
			tsteEntity entity = new tsteEntity(MobEntities.mosquito,world);
			entity.refreshPositionAndAngles(spawnPos.x, spawnPos.y+2f, spawnPos.z, player.getYaw(), 0);
			world.spawnEntity(entity);

		});

	}
	private boolean isSafeAroundBed(PlayerEntity player, BlockPos bedPos) {
		Box area = new Box(bedPos).expand(8, 5, 8); // 水平8格，垂直5格
		List<HostileEntity> monsters = player.getWorld().getEntitiesByClass(
				HostileEntity.class, area, e -> e.isAlive()
		);
		return monsters.isEmpty();
	}
	/*public class PacketIdentifiers {
		public static final CustomPayload.Id<GrabPayload> GRAB_PACKET_ID = GrabPayload.ID;

		public static void register() {
			// 注册 Payload 类型
			registerGlobalReceiver(
					GRAB_PACKET_ID,
					GrabPayload::new
			);

			// 注册服务端处理器
			ServerPlayNetworking.registerGlobalReceiver(
					GRAB_PACKET_ID,
					(payload, context) -> {
						ServerPlayerEntity player = context.player();
						player.getServer().execute(() -> {
							// 获取玩家瞄准的生物
							Entity target = getTargetedEntity(player, 10.0);

							if (target instanceof LivingEntity) {
								// 拉取生物
								pullEntity(player, (LivingEntity) target);

								// 造成伤害
								target.damage(
										player.getWorld(),
										player.getDamageSources().playerAttack(player),
										5.0f
								);

								// 添加粒子效果
								player.getWorld().addParticleClient(
										ParticleTypes.WAX_OFF,
										target.getX(),
										target.getBodyY(0.5),
										target.getZ(),
										0, 0, 0
								);
							}
						});
					}
			);
		}

		// getTargetedEntity 和 pullEntity 方法保持不变...
	}*/
}