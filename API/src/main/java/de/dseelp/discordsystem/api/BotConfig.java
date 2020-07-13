package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.config.JsonConfig;
import lombok.SneakyThrows;

import java.io.File;

public class BotConfig {
    private static JsonConfig config;

    @SneakyThrows
    public static void load() {
        File file = new File("config.json");
        if (!file.exists()) file.createNewFile();
        config = JsonConfig.load(file);
        config.setDefault("token", "Enter Token here!");
        config.save(file);
    }

    public static String getToken() {
        return config.getString("token");
    }
}
