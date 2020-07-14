package de.dseelp.discordsystem.api.commands;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CommandType {
    CONSOLE,
    DISCORD_GUILD,
    DISCORD_PRIVATE;

    public static boolean isSupported(CommandSender sender, CommandType[] types) {
        if (sender instanceof ConsoleCommandSender) {
            return Arrays.stream(types).anyMatch(type -> type == CommandType.CONSOLE);
        }else if (sender instanceof DiscordGuildCommandSender) {
            return Arrays.stream(types).anyMatch(type -> type == CommandType.DISCORD_GUILD);
        }
        return false;
    }

    public static final CommandType[] ALL = values();

    public static final CommandType[] GUILD_AND_CONSOLE = new CommandType[]{DISCORD_GUILD, CONSOLE};
}
