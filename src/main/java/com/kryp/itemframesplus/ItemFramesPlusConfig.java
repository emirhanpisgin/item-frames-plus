package com.kryp.itemframesplus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kryp.itemframesplus.networking.ItemFramesPlusPacketSender;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ItemFramesPlusConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Options options;
    private static File configFile;

    public static class Options {
        private Boolean invisibleItemFrames = true;

        public Boolean getInvisibleItemFrames() {
            return invisibleItemFrames;
        }

        public void setInvisibleItemFrames(Boolean invisibleItemFrames) {
            this.invisibleItemFrames = invisibleItemFrames;
            ItemFramesPlusPacketSender.sendInvisibleItemFrames(invisibleItemFrames);
            saveConfig();
        }
    }

    public static void registerConfig() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(ItemFramesPlus.MOD_ID + ".json");
        configFile = path.toFile();

        if (!configFile.exists()) {
            createConfig();
        }

        loadConfig(path);
    }

    private static void createConfig() {
        options = new Options();
        saveConfig();
    }

    private static void loadConfig(Path configFilePath) {
        try {
            String jsonString = Files.readString(configFilePath);
            options = GSON.fromJson(jsonString, Options.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Options getOptions() {
        return options;
    }

    private static void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(GSON.toJson(options));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
