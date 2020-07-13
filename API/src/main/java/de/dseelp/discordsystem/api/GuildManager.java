package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import de.dseelp.discordsystem.utils.config.JsonConfig;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public interface GuildManager {

    GuildConfig getGuildConfig(Guild guild);

    GuildConfig getGuildConfig(long id);

    void save(GuildConfig config);
}
