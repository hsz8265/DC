package com.bp.darkcuisine.entity.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.registerGlobalReceiver;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

public record GrabPayload() implements CustomPayload {
    // 1. 定义Payload ID
    public static final CustomPayload.Id<GrabPayload> ID =
            new CustomPayload.Id<>(Identifier.of("dark-cuisine", "grab"));

    // 2. 定义CODEC（序列化/反序列化逻辑）
    public static final PacketCodec<PacketByteBuf, GrabPayload> CODEC =
            PacketCodec.ofStatic(
                    (payload, buf) -> {
                        // 序列化 - 空负载不需要写入任何数据
                    },
                    buf -> {
                        // 反序列化 - 创建新实例
                        return new GrabPayload();
                    }
            );

    // 3. 实现接口方法
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
