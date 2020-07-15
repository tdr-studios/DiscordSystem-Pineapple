package de.tdrstudios.api.DevTools;

import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class DevTools {

    public void alert(String msg, Color color) {
        TextChannel channel;
        channel = event.getGuild().getTextChannelsByName("CHANNEL_NAME",true).get(0);

        channel.sendMessage("Test")
    }
}
