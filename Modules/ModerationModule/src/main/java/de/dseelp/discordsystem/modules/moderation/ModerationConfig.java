package de.dseelp.discordsystem.modules.moderation;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.entities.Guild;

public class ModerationConfig {
    private static final String moduleKey = "moderationModule";
    public static JsonDocument getDocument(Guild guild) {
        GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(guild);
        JsonDocument document = guildConfig.getDocument();
        if (!document.has(moduleKey)) {
            document.addDocument(moduleKey, new JsonDocument());
            Discord.getGuildManager().save(guildConfig);
        }
        return document.getDocument(moduleKey);
    }

    public static void setEntry(Guild guild, String key, String value) {
        GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(guild);
        JsonDocument document = guildConfig.getDocument();
        if (!document.has(moduleKey)) {
            document.addDocument(moduleKey, new JsonDocument());
            Discord.getGuildManager().save(guildConfig);
        }
        document.getDocument(moduleKey).add(key, value);
        Discord.getGuildManager().save(guildConfig);
    }


    public static void setEntry(Guild guild, String key, Number value) {
        GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(guild);
        JsonDocument document = guildConfig.getDocument();
        if (!document.has(moduleKey)) {
            document.addDocument(moduleKey, new JsonDocument());
            Discord.getGuildManager().save(guildConfig);
        }
        document.getDocument(moduleKey).add(key, value);
        Discord.getGuildManager().save(guildConfig);
    }

    public static void setEntry(Guild guild, String key, Boolean value) {
        GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(guild);
        JsonDocument document = guildConfig.getDocument();
        if (!document.has(moduleKey)) {
            document.addDocument(moduleKey, new JsonDocument());
            Discord.getGuildManager().save(guildConfig);
        }
        document.getDocument(moduleKey).add(key, value);
        Discord.getGuildManager().save(guildConfig);
    }

    public static void setEntry(Guild guild, String key, Character value) {
        GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(guild);
        JsonDocument document = guildConfig.getDocument();
        if (!document.has(moduleKey)) {
            document.addDocument(moduleKey, new JsonDocument());
            Discord.getGuildManager().save(guildConfig);
        }
        document.getDocument(moduleKey).add(key, value);
        Discord.getGuildManager().save(guildConfig);
    }

    public static long getSuggestionChannelId(Guild guild) {
        JsonDocument document = getDocument(guild);
        if (!document.has("suggestionChannel")) return -1;
        return document.getLong("suggestionChannel");
    }
    public static long getBugReportChannelId(Guild guild) {
        JsonDocument document = getDocument(guild);
        if (!document.has("bugReportChannel")) return -1;
        return document.getLong("bugReportChannel");
    }

    public static void setSuggestionChannelId(Guild guild, long id)  {
        setEntry(guild, "suggestionChannel", id);
    }

    public static void setBugReportChannelId(Guild guild, long id)  {
        setEntry(guild, "bugReportChannel", id);
    }
}
