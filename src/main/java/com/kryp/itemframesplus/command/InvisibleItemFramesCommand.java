package com.kryp.itemframesplus.command;

import com.kryp.itemframesplus.ItemFramesPlusConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class InvisibleItemFramesCommand {
    public static int run(CommandContext<FabricClientCommandSource> context) {

        Boolean currentValue = ItemFramesPlusConfig.getOptions().getInvisibleItemFrames();

        Boolean requestedValue = context.getArgument("boolean", Boolean.class);

        if(currentValue.equals(requestedValue)) {
            context.getSource().sendFeedback(Text.literal(Text.translatable("command.itemframesplus.invisibleItemFrames.alreadySet").getString() + currentValue.toString()));

            return -1;
        }else {
            ItemFramesPlusConfig.getOptions().setInvisibleItemFrames(requestedValue);

            context.getSource().sendFeedback(Text.literal(Text.translatable("command.itemframesplus.invisibleItemFrames.nowSetTo").getString() + requestedValue.toString()));

            return 1;
        }


    }

    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(ClientCommandManager.literal("itemframesplus")
                .then(ClientCommandManager.literal("invisibleItemFrames").then((ArgumentBuilder<FabricClientCommandSource, ?>)ClientCommandManager.argument("boolean", BoolArgumentType.bool()).executes(InvisibleItemFramesCommand::run))));
    }
}
