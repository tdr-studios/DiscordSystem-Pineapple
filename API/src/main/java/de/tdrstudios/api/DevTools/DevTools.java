package de.tdrstudios.api.DevTools;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class DevTools {

    private static LogSystem logsystem;
    public static void initLog() {
        logsystem = LoggerRegistry.get("devtools");
    }


    public static void alert(String msg, Color color, Guild guild, String title, Module module) {


        TextChannel channel = guild.getSystemChannel();
        JsonDocument doc = Discord.getGuildManager().getGuildConfig(guild).getDocument();
        if(Discord.getGuildManager().getGuildConfig(guild).getDocument().getString("AlertChannel").isEmpty()) {
            System.out.println("[DevTools] A Module has send a Message to the AlertChannel but the Channel was not setuped yet use +setup AlertChannel to fix this Bug");
        }else {
            channel = (TextChannel) guild.getGuildChannelById(doc.getString("AlertChannel"));

        }

        EmbedBuilder eb = createNormalBuilder(title, msg);

        eb.setColor(color);
        eb.setFooter("This Text was send by " + module.getName());
        channel.sendMessage(eb.build()).queue();


    }

    public static void alert(String msg, Color color, Guild guild, TextChannel channel, String Title, Module module) {
        EmbedBuilder eb = createNormalBuilder(Title, msg);

        eb.setColor(color);
        eb.setFooter("This Text was send by " + module.getName());
        channel.sendMessage(eb.build()).queue();

    }

    public static void sendPrivateEmbed(Member member, EmbedBuilder eb) {

        //PrivateChannel complete = member.getUser().openPrivateChannel().complete();
        //complete.sendMessage(eb.build()).queue();
        logsystem.debug("Member -> " + member);
        member.getUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(eb.build()).queue();


        });
        logsystem.write("[PrivateMSG] Send a Embed to " + member.getUser().getAsTag());
      //  if(eb.build().getTitle() == null) {
        //    eb.setTitle("Title");
        //}
        logsystem.write(" -------------------------------------------------");
        if(eb.build().getTitle() != null) {
            logsystem.write("   Title: " + eb.build().getTitle());
        }
        logsystem.write("   Text:  " + eb.build().getDescription());
        if(eb.build().getFields().isEmpty()) {
        }else {
            logsystem.write(" Fields:  " + eb.build().getFields());
        }
        if(eb.build().getAuthor() != null){
            logsystem.write(" Author:  " + eb.build().getAuthor());
        }
        if(eb.build().getUrl() != null){
            logsystem.write("    URL:  " + eb.build().getUrl());
        }

        logsystem.write(" -------------------------------------------------");


    }

    public static void sendPrivateText(Member user, String text) {
        user.getUser().openPrivateChannel().queue();
        user.getUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(text).queue();
        });
    }
}

