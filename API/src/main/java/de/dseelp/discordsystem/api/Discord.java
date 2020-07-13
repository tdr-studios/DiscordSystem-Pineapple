package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.CommandSystem;
import lombok.Getter;
import lombok.Setter;

public class Discord {
    @Setter
    @Getter
    private static DiscordBot bot;

    @Getter
    @Setter
    private static GuildManager guildManager;
}
