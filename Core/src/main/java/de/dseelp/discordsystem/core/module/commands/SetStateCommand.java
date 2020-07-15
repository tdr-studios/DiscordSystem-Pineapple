package de.dseelp.discordsystem.core.module.commands;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.api.event.EventHandler;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.api.events.system.CommandListRegenerateEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import de.dseelp.discordsystem.api.DiscordBot;
import net.dv8tion.jda.api.entities.Activity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetStateCommand extends Command {

    public SetStateCommand() {



        super(null, "A simple SetSate Command", CommandType.GUILD_AND_CONSOLE, "setstate");

    }


    @Override
    public void execute(CommandSender sender, String[] args, Command command) {

        if (sender instanceof ConsoleCommandSender) {
            System.out.println("[Debug] Command by Console");
            System.out.println("[Debug] -> " + args.toString());
            String msg = args[];



            DiscordBot.getShardManager().setActivity(Activity.streaming(args[0], "https://tdrstudios.de"));


        } else if (sender instanceof DiscordGuildCommandSender) {

            StringBuilder builder = new StringBuilder();
            boolean first = true;
            for (String arg : args) {
                if (!first) builder.append(", ");
                builder.append(arg);
                first = false;
            }
            System.out.println("[Debug] Command by Guild");
            System.out.println("[Debug] -> " + builder.toString());

            EmbedBuilder eb = EmbedUtils.createSuccessBuilder("DEBUG", builder.toString());
            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender) sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build()).queue();
        }
    }
}