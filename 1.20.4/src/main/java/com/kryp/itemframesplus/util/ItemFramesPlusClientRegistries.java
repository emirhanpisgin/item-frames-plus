package com.kryp.itemframesplus.util;

import com.kryp.itemframesplus.command.InvisibleItemFramesCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ItemFramesPlusClientRegistries {
    public static void register(){
        registerCommands();
    }

    private static void registerCommands(){
        ClientCommandRegistrationCallback.EVENT.register(InvisibleItemFramesCommand::register);
    }
}
