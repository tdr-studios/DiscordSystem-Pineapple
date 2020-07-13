package de.dseelp.discordsystem.api.commands;

public enum CommandType {
    CONSOLE,
    DISCORD_GUILD,
    DISCORD_PRIVATE,
    GUILD_AND_CONSOLE;

    public static boolean isSupported(CommandSender sender, CommandType type) {
        if (sender instanceof ConsoleCommandSender) {
            if (type == CONSOLE || type == GUILD_AND_CONSOLE) return true;
        }
        if (sender instanceof DiscordGuildCommandSender) {
            if (type == GUILD_AND_CONSOLE || type == DISCORD_GUILD) return true;
        }
        return false;
    }
}
