package de.tdrstudios.api.DevTools;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.api.modules.Module;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class DevTools {

    public static void alert(String msg, Color color, Guild guild, String title, Module module) {
        TextChannel channel = guild.getSystemChannel();
        JsonDocument doc = Discord.getGuildManager().getGuildConfig(guild).getDocument();
        if(Discord.getGuildManager().getGuildConfig(guild).getDocument().getString("AlertChannel").isEmpty()) {
            System.out.println("[DevTools] A Module has send a Massage to the AlertChannel but the Channel was not setuped yet use +setup AlertChannel to fix this Bug");
        }else {
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
}
