package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.config.JsonConfig;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.OnlineStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        config.setDefault("autoUpdates", true);
        config.setDefault("StartWithGUI", true);
        List<String> defaultRepos = new ArrayList<>();
        defaultRepos.add("https://api.tdrstudios.de");
        config.setDefaultStringList("moduleRepositories", defaultRepos);
        config.setDefault("allowActivityChange", true);
        config.save(file);
    }

    public static String getActivityName() {
        return config.getString("activity");
    }

    public static OnlineStatus getOnlineStatus() {
        return OnlineStatus.valueOf(config.getString("defaultOnlineStatus").toUpperCase());
    }

    public static boolean isAutoUpdates() {
        return config.getBoolean("autoUpdates");
    }

    public static String[] getModuleRepositoryUrls() {
        Collection<String> moduleRepositories = config.getStringList("moduleRepositories");
        if (moduleRepositories == null) return new String[0];
        return moduleRepositories.toArray(new String[moduleRepositories.size()]);
    }

    public static ActivityType getActivityType() {
        return ActivityType.valueOf(config.getString("activityType").toUpperCase());
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
    public static String getallowActivityChange() {
        return config.getString("allowActivityChange");
    }
}
