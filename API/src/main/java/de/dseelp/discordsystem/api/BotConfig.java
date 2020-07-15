package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.config.JsonConfig;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.OnlineStatus;

import java.io.File;

public class BotConfig {
    private static JsonConfig config;

    @SneakyThrows
    public static void load() {

        File file = new File("config.json");
        if (!file.exists()) file.createNewFile();
        config = JsonConfig.load(file);
        config.setDefault("token", "Enter Token here!");
        config.setDefault("activity", "TDRStudios | System");
        config.setDefault("activityType", ActivityType.PLAYING.toString());
        config.setDefault("defaultOnlineStatus", OnlineStatus.ONLINE.toString());
        config.setDefault("commandPrefix", "+");
        config.setDefault("Say-Command Header", "Massage");
        config.save(file);
    }

    public static String getActivityName() {
        return config.getString("activity");
    }

    public static OnlineStatus getOnlineStatus() {
        return OnlineStatus.valueOf(config.getString("defaultOnlineStatus"));
    }

    public static ActivityType getActivityType() {
        return ActivityType.valueOf(config.getString("activityType"));
    }

    public static String getCommandPrefix() {
        return config.getString("commandPrefix");
    }

    public static String getToken() {
        return config.getString("token");
    }
    public static String getSayCommandHeader() {
        return config.getString("Say-Command Header");
    }
}
