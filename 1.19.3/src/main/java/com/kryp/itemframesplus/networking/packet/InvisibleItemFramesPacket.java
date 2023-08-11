package com.kryp.itemframesplus.networking.packet;

import com.kryp.itemframesplus.util.ItemFramesPlusPlayerPreferences;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class InvisibleItemFramesPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        ItemFramesPlusPlayerPreferences.addPlayer(player.getUuid(), buf.readBoolean());
    }
}
