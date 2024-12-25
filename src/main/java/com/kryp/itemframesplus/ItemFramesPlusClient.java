package com.kryp.itemframesplus;

import com.kryp.itemframesplus.util.ItemFramesPlusClientRegistries;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ItemFramesPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemFramesPlusConfig.registerConfig();
        ItemFramesPlusClientRegistries.register();
    }
}