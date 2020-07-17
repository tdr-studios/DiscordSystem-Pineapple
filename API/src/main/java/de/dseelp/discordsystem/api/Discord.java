package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.event.EventManager;
import de.dseelp.discordsystem.api.reload.ReloadManager;
import de.dseelp.discordsystem.utils.console.Console;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Discord {
    @Getter
    private static String Version = "C-ALPHA -- Grapefruit 1";
    @Getter
    @Setter
    private static boolean maintenance = false;

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

    @Getter
    @Setter
    private static ReloadManager reloadManager;

    @Getter
    @Setter
    private static Console console;


}
