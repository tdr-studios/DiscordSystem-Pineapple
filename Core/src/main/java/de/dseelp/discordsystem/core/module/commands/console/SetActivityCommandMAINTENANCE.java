package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.api.ActivityType;
import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.tdrstudios.utils.SenderType;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;


public class SetActivityCommandMAINTENANCE extends Command{

    public SetActivityCommandMAINTENANCE() {
        super(new NullPermission(),"Set a Custom Activity",CommandType.CONSOLE,"setactivity");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {

        EmbedBuilder eb = EmbedUtils.createNormalBuilder("Bot-System", "Cant resolve Command" + System.lineSeparator() +" -> ErrorCode #001 in SetActivityCommand");
        if(sender instanceof DiscordGuildCommandSender)
        {

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
