package com.kryp.itemframesplus.networking;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

@Environment(EnvType.CLIENT)
public class ItemFramesPlusPacketSender {

    public static void register(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        sendInvisibleItemFrames(ItemFramesPlusConfig.getOptions().getInvisibleItemFrames());
    }

    public static void sendInvisibleItemFrames(Boolean invisibleItemFrames){
        PacketByteBuf invisibleItemFramesPacket = new PacketByteBuf(Unpooled.buffer());
        invisibleItemFramesPacket.writeBoolean(invisibleItemFrames);

        ClientPlayNetworking.send(ItemFramesPlusPacketHandler.INVISIBLE_ITEM_FRAMES_ID, invisibleItemFramesPacket);
    }
}
