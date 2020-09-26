package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.awt.*;
import java.time.Instant;

public class SetStateCommandMAINTENANCE extends Command {

    public SetStateCommandMAINTENANCE() {

        super(new NullPermission(), "A simple SetSate Command", CommandType.CONSOLE, "setstate");


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

        }
    }
}