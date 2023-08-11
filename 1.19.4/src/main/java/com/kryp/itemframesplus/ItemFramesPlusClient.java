package com.kryp.itemframesplus;

import com.kryp.itemframesplus.networking.ItemFramesPlusPacketSender;
import com.kryp.itemframesplus.util.ItemFramesPlusClientRegistries;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

@Environment(EnvType.CLIENT)
public class ItemFramesPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemFramesPlusConfig.registerConfig();
        ItemFramesPlusClientRegistries.register();
        ClientPlayConnectionEvents.JOIN.register(ItemFramesPlusPacketSender::register);
    }
}