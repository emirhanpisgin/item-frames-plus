package com.kryp.itemframesplus;

import com.kryp.itemframesplus.networking.packet.InvisibleItemFramesPacket;
import com.kryp.itemframesplus.util.ItemFramesPlusPlayerPreferences;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemFramesPlus implements ModInitializer {
	public static final String MOD_ID = "itemframesplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		PayloadTypeRegistry.playC2S().register(InvisibleItemFramesPacket.PACKET_ID, InvisibleItemFramesPacket.PACKET_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(InvisibleItemFramesPacket.PACKET_ID, ((payload, context) -> {
			ItemFramesPlusPlayerPreferences.addPlayer(context.player().getUuid(), payload.bool());
		}));

		LOGGER.info("Initializing Item Frames+!");

	}
}