package de.dseelp.discordsystem.core.module.commands;

import de.dseelp.discordsystem.api.ActivityType;
import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.tdrstudios.utils.SenderType;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;

import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.time.Instant;


public class SetActivityCommand extends Command{

    public SetActivityCommand() {
        super(null,"Set a Custom Activity",CommandType.GUILD_AND_CONSOLE,"setactivity");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        EmbedBuilder eb = EmbedUtils.createNormalBuilder("Bot-System", "Cant resolve Command" + System.lineSeparator() +" -> ErrorCode #001 in SetActivityCommand");
        if(sender instanceof DiscordGuildCommandSender)
        {
            DiscordGuildCommandSender guildsender = (DiscordGuildCommandSender) sender;

            boolean wip= true;
            if(args.length == 0) {

                HelpSite(SenderType.Guild,eb);


            } else {
                if(args[0].equalsIgnoreCase("%reset%")) {
                    System.out.println("[Bot] A User reset the Custom Status of the Bot!");
                    System.out.println("       User -> " + guildsender.getAuthor().getName() + guildsender.getAuthor().getAsTag());
                    System.out.println("Old-Activity->" + " -- Work in Progress--");
                    System.out.println("New-Activity->" + " -- Work in Progress--");

                    if(Discord.isMaintenance()) {
                        Discord.getBot().setActivity(ActivityType.WATCHING,"Maintenance");
                        eb.setDescription("The Activity was set to Maintenance");
                        eb.setColor(Color.GREEN);
                    }else {
                        Discord.getBot().setActivity(BotConfig.getActivityType(),BotConfig.getActivityName());
                        eb.setDescription("The Activity was set to "+ BotConfig.getActivityName());
                        eb.setColor(Color.GREEN);
                    }

                }else {
                    StringBuilder builder = new StringBuilder();
                    boolean first = true;
                    for (String arg : args) {
                        if (!first)  builder.append(" ");
                        builder.append(arg);
                        first = false;
                    }
                    if(Discord.isMaintenance()) {
                        Discord.getBot().setActivity(ActivityType.WATCHING,"Maintenance");
                        eb.setDescription("The Activity was set to Maintenance");
                        eb.setColor(Color.GREEN);
                        System.out.println("[Bot] A User try to set a Custom Status of the Bot!");
                        System.out.println("       User -> " + guildsender.getAuthor().getName() + guildsender.getAuthor().getAsTag());
                        System.out.println("Error-> " + "Maintenance-Mode is on");
                        System.out.println("New-Activity-> " + builder.toString());
                    }else {
                        System.out.println("[Bot] A User set a Custom Status of the Bot!");
                        System.out.println("       User -> " + guildsender.getAuthor().getName() + guildsender.getAuthor().getAsTag());
                        System.out.println("Old-Activity-> " + " -- Work in Progress--");
                        System.out.println("New-Activity-> " + builder.toString());
                        Discord.getBot().setActivity(ActivityType.PLAYING, builder.toString()); // DE: Soll gegen Custom ersetzt werden, wenn Fehler im EventSystem gefixed ist!
                        eb.setDescription("The Activity was set to "+ builder.toString() + System.lineSeparator() +System.lineSeparator() +" This is a temp version! In the next Time the Activity will be shown as Custom and not Playing! (; the Dev Team!");
                        eb.setColor(Color.GREEN);
                    }

                }


            };






            buildEmbed(eb,sender);
        }
        if(sender instanceof ConsoleCommandSender) {

            if(args.length==0) {
                HelpSite(SenderType.CONSOLE,eb);

            }else {
                if(args[0].equalsIgnoreCase("%reset%")) {
                    if(Discord.isMaintenance()) {
                        Discord.getBot().setActivity(ActivityType.WATCHING,"Maintenance");
                        System.out.println("The Activity was set to Maintenance");

                    }else {
                        Discord.getBot().setActivity(BotConfig.getActivityType(),BotConfig.getActivityName());
                        System.out.println("The Activity was set to "+ BotConfig.getActivityName());

                    }
                } else {
                    StringBuilder builder = new StringBuilder();
                    boolean first = true;
                    for (String arg : args) {
                        if (!first)  builder.append(" ");
                        builder.append(arg);
                        first = false;
                    }
                    Discord.getBot().setActivity(ActivityType.PLAYING, builder.toString());
                    System.out.println("");



                }
            }


            //Console Sender!!

        }
    }
    public void HelpSite(SenderType type, EmbedBuilder embedBuilder) {
        if(type.equals(SenderType.CONSOLE)){
            System.out.println("-----------------Help-----------------");
            System.out.println("You can coose between this:");
            System.out.println(" ");
            System.out.println("- %reset% -> Reset");
            System.out.println("- <yourtext> Set your Text");
            System.out.println(" ");
            System.out.println("-----------------Help-----------------");

        }
        if(type.equals(SenderType.Guild)) {
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription("You can Choose between this:" + System.lineSeparator() + System.lineSeparator() +  "%reset% - reset the Activity" + System.lineSeparator() + "<your status> - Set a Custom Status");
        }
    }
    public void buildEmbed(EmbedBuilder eb, CommandSender sender) {

        EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender)sender).getAuthor());
        EmbedUtils.setTimestamp(eb, Instant.now());
        sender.sendMessage(eb.build()).queue();
    }
}
