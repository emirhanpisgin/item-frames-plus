package com.kryp.itemframesplus.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemFramesPlusPlayerPreferences {
    private static final Map<UUID, Boolean> playerPreferences = new HashMap<>();

    public static Map<UUID, Boolean> getPlayerPreferences() {
        return playerPreferences;
    }

    public static void addPlayer(UUID uuid, Boolean preference){
        playerPreferences.put(uuid, preference);
    }

    public static Boolean getPreference(UUID uuid){
        return playerPreferences.get(uuid);
    }
}
