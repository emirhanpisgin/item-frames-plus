package com.kryp.itemframesplus.util;

import com.kryp.itemframesplus.command.InvisibleItemFramesCommand;
import com.kryp.itemframesplus.networking.packet.InvisibleItemFramesPacket;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ItemFramesPlusClientRegistries {
    public static void register(){
        registerCommands();
    }

    private static void registerCommands(){
        ClientCommandRegistrationCallback.EVENT.register(InvisibleItemFramesCommand::register);
    }
}
