package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.entities.Guild;

public interface GuildManager {

    GuildConfig getGuildConfig(Guild guild);

    GuildConfig getGuildConfig(long id);

    void save(GuildConfig config);
}
