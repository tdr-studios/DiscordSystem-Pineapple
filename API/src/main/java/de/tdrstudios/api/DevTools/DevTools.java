package de.tdrstudios.api.DevTools;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.console.Console;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.modules.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.Document;
import java.awt.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class DevTools {


    public static void alert(String msg, Color color, Guild guild, String title, Module module) {
        TextChannel channel = guild.getSystemChannel();
        JsonDocument doc = Discord.getGuildManager().getGuildConfig(guild).getDocument();
        if (Discord.getGuildManager().getGuildConfig(guild).getDocument().getString("AlertChannel").isEmpty()) {
            System.out.println("[DevTools] A Module has send a Massage to the AlertChannel but the Channel was not setuped yet use +setup AlertChannel to fix this Bug");
        } else {
            channel = (TextChannel) guild.getGuildChannelById(doc.getString("AlertChannel"));

        }

        EmbedBuilder eb = createNormalBuilder(title, msg);

        eb.setColor(color);
        eb.setFooter("This Text was send by " + module.getName());
        channel.sendMessage(eb.build()).queue();


    }

    public void alert(String msg, Color color, Guild guild, TextChannel channel, String Title, Module module) {
        EmbedBuilder eb = createNormalBuilder(Title, msg);

        eb.setColor(color);
        eb.setFooter("This Text was send by " + module.getName());
        channel.sendMessage(eb.build()).queue();

    }

        public static void TimeOutput(String msg, Module module) {
            Date date = new Date();
            System.out.println("-------------------------------------");
            System.out.println(date.toString());
            System.out.println(msg + "send by " + module.getName());
            System.out.println("-------------------------------------");
        }
         public static void TimeOutput(String msg) {
        Date date = new Date();
        System.out.println("-------------------------------------");
        System.out.println(date.toString());
        System.out.println(msg);
        System.out.println("-------------------------------------");
         }


    @Deprecated
    public void rawWrite (String msg) {
        Console console = ConsoleSystem.getConsole();
        console.write(msg);

    }





}

