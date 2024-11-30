package com.kryp.itemframesplus;

import com.kryp.itemframesplus.networking.ItemFramesPlusPacketHandler;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemFramesPlus implements ModInitializer {
	public static final String MOD_ID = "itemframesplus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Item Frames+!");
		ItemFramesPlusPacketHandler.registerC2SPackets();

	}
}