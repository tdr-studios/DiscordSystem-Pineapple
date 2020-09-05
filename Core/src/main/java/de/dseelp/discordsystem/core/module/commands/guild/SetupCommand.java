package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.api.setup.Setup;
import de.dseelp.discordsystem.api.setup.SetupManager;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;

public class SetupCommand extends Command {

    public SetupCommand() {
        super(null, "Execute a Setup", CommandType.DISCORD_GUILD, "setup");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        SetupManager setupManager = Discord.getSetupManager();
        if (args.length == 0) {
            sendHelp(sender);
            return;
        }
        if (!setupManager.setup(sender, args)) {
            sendHelp(sender);
        }
    }

    private void sendHelp(CommandSender sender) {
        StringBuilder builder = new StringBuilder();
        for (Setup setup : Discord.getSetupManager().getSetups()) {
            builder.append(setup.getName().toLowerCase());
            builder.append(" - ");
            builder.append(setup.getDescription() == null ? "No Description present!" : setup.getDescription());
            builder.append(System.lineSeparator());
        }
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Please use: setup <Setup>");
            sender.sendMessage("Setups:");
            sender.sendMessage(builder.toString());
        }else if (sender instanceof DiscordGuildCommandSender) {
            EmbedBuilder eb = EmbedUtils.createErrorBuilder("Setups", builder.toString());
            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender) sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build());
        }
    }
}
