package com.kryp.itemframesplus.networking;

import com.kryp.itemframesplus.ItemFramesPlus;
import com.kryp.itemframesplus.networking.packet.InvisibleItemFramesPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ItemFramesPlusPacketHandler {
    public static final Identifier INVISIBLE_ITEM_FRAMES_ID = new Identifier(ItemFramesPlus.MOD_ID, "invisibleitemframes");
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(INVISIBLE_ITEM_FRAMES_ID, InvisibleItemFramesPacket::receive);
    }
}
