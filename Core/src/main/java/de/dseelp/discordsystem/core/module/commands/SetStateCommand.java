package de.dseelp.discordsystem.core.module.commands;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.awt.*;
import java.time.Instant;

public class SetStateCommand extends Command {

    public SetStateCommand() {

        super(new NullPermission(), "A simple SetSate Command", CommandType.GUILD_AND_CONSOLE, "setstate");


    }


    @Override
    public void execute(CommandSender sender, String[] args, Command command) {

        if (sender instanceof ConsoleCommandSender) {
            //String msg = args[];
            if(args.length == 0) {
                System.out.println("                                      ");
                System.out.println("[Help] You can choose between this:");
                System.out.println("[Help] ONLINE");
                System.out.println("[Help] INVISIBLE");
                System.out.println("[Help] IDLE");
                System.out.println("                                      ");


            } else if (args[0].equalsIgnoreCase("online")) {
                Discord.getBot().setStatus(OnlineStatus.ONLINE);
                System.out.println("[Bot] The Bot is now in mode : " + args[0]);
            }else if (args[0].equalsIgnoreCase("invisible")){
                System.out.println("[Bot] The Bot is now in mode : " + args[0]);
                Discord.getBot().setStatus(OnlineStatus.INVISIBLE);
            }else if (args[0].equalsIgnoreCase("idle")) {
                System.out.println("[Bot] The Bot is now in mode : " + args[0]);
                Discord.getBot().setStatus(OnlineStatus.IDLE);
            }





        } else if (sender instanceof DiscordGuildCommandSender) {

            StringBuilder builder = new StringBuilder();

            EmbedBuilder eb = EmbedUtils.createNormalBuilder("Bot-System","");
            if(args.length == 0) {
                eb.setColor(Color.RED);
                eb.setDescription("You can choose between this:" + System.lineSeparator() +" Online " + System.lineSeparator() + " Idle ");

            } else if(args[0].equalsIgnoreCase("online")) {
                Discord.getBot().setStatus(OnlineStatus.ONLINE);
                eb.setColor(Color.GREEN);
                eb.setDescription("The Status was set to [ONLINE]");

            }
            else if(args[0].equalsIgnoreCase("idle")) {
                Discord.getBot().setStatus(OnlineStatus.IDLE);
                eb.setColor(Color.ORANGE);
                eb.setDescription("The Status was set to [IDLE]");

            }
            else if(args[0].equalsIgnoreCase("invisible")) {

                eb.setColor(Color.RED);
                eb.setDescription("You cant set the Status to invisible! You can do that in the Bot Console!");

            }
            else if(args[0].equalsIgnoreCase("DO_NOT_DISTURB")) {


                eb.setColor(Color.RED);
                eb.setDescription("You cant set the Status to  DO_NOT_DISTURB! This is only for maintenance!");

            }else {
                eb.setColor(Color.RED);
                eb.setDescription("You can choose between this:" + System.lineSeparator() +" Online " + System.lineSeparator() + " Idle ");
            }



            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender) sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build()).queue();
        }
    }
}