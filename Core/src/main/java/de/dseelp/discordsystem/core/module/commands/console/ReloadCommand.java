package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.api.reload.ReloadManager;
import de.dseelp.discordsystem.api.reload.Reloadable;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.time.Instant;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super(null, "Reloads the application", CommandType.CONSOLE, "rl", "reload");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        ReloadManager reloadManager = Discord.getReloadManager();
        if (args.length == 0) {
            sendHelp(sender);
            return;
        }
        if (!reloadManager.reload(args[0])) {
            sendHelp(sender);
        }
    }

    private void sendHelp(CommandSender sender) {
        StringBuilder builder = new StringBuilder();
        for (Reloadable reload : Discord.getReloadManager().getReloads()) {
            builder.append(reload.getReloadName().toLowerCase());
            builder.append(" - ");
            builder.append(reload.getDescription() == null ? "No Description present!" : reload.getDescription());
            builder.append(System.lineSeparator());
        }
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Please use: reload <Reload>");
            sender.sendMessage("Reloads:");
            sender.sendMessage(builder.toString());
        }else if (sender instanceof DiscordGuildCommandSender) {
            EmbedBuilder eb = EmbedUtils.createErrorBuilder("Reloads", builder.toString());
            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender) sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build());
        }
    }
}
