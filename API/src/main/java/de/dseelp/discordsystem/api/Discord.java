package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.event.EventManager;
import lombok.Getter;
import lombok.Setter;

public class Discord {
    @Setter
    @Getter
    private static DiscordBot bot;

    @Getter
    @Setter
    private static GuildManager guildManager;

    @Getter
    @Setter
    private static CommandSystem commandSystem;

    @Getter
    @Setter
    private static EventManager eventManager;
}
