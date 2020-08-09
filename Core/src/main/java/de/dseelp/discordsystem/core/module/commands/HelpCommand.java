package de.dseelp.discordsystem.core.module.commands;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.api.event.EventHandler;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.api.events.system.CommandListRegenerateEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpCommand extends Command implements Listener {

    public HelpCommand() {
        super(null, "A simple HelpCommand", CommandType.GUILD_AND_CONSOLE, "help", "?");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        StringBuilder builder = new StringBuilder();
        for (Command cmd : guildCommands) {
            if (!sender.hasPermission(cmd.getPermission())) continue;
            builder.append("[").append(toCommaSeperatedString(cmd.getNames())).append("] - ").append(cmd.getDescription());
            builder.append(System.lineSeparator());
        }
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(builder.toString());
        }else if (sender instanceof DiscordGuildCommandSender) {
            EmbedBuilder eb = EmbedUtils.createSuccessBuilder("Help", builder.toString());
            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender)sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build()).queue();
        }
    }

    private String toCommaSeperatedString(String[] strings) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String s : strings) {
            if (!first) builder.append(", ");
            builder.append(s);
            first = false;
        }
        return builder.toString();
    }

    private Command[] consoleCommands = new Command[]{};
    private Command[] guildCommands = new Command[]{};
    private Command[] privateCommands = new Command[]{};


    @EventHandler
    public void onRegenerate(CommandListRegenerateEvent event) {
        List<Command> consoleCommands = new ArrayList<>();
        List<Command> guildCommands = new ArrayList<>();
        List<Command> privateCommands = new ArrayList<>();
        for (Command command : Discord.getCommandSystem().getCommands()) {
            if (Arrays.stream(command.getTypes()).anyMatch(type -> type == CommandType.CONSOLE)) {
                consoleCommands.add(command);
            }
            if (Arrays.stream(command.getTypes()).anyMatch(type -> type == CommandType.DISCORD_GUILD)) {
                guildCommands.add(command);
            }
            if (Arrays.stream(command.getTypes()).anyMatch(type -> type == CommandType.DISCORD_PRIVATE)) {
                privateCommands.add(command);
            }
        }
        this.consoleCommands = consoleCommands.toArray(new Command[consoleCommands.size()]);
        this.guildCommands = guildCommands.toArray(new Command[guildCommands.size()]);
        this.privateCommands = privateCommands.toArray(new Command[privateCommands.size()]);
    }

}
