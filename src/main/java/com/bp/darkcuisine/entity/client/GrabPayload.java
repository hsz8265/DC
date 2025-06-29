package com.bp.darkcuisine.entity.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.registerGlobalReceiver;

public record GrabPayload() implements CustomPayload {
    public static final Id<GrabPayload> ID = new Id<>(Identifier.of("dark-cuisine", "grab"));

    // 从字节缓冲区构造
    public GrabPayload(PacketByteBuf buf) {
        this(); // 不需要读取数据
    }

    public GrabPayload(GrabPayload grabPayload, ClientPlayNetworking.Context context) {
        this();
    }
    public GrabPayload() {
        // 空构造函数
    }

    // 写入字节缓冲区
    public void write(PacketByteBuf buf) {
        // 空负载，不需要写入数据
    }

    @Override
    public Id<GrabPayload> getId() {
        return ID;
    }
}
