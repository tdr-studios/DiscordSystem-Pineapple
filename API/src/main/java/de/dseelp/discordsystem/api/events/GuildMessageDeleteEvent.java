package de.dseelp.discordsystem.api.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class GuildMessageDeleteEvent extends GuildMessageEvent {

    public GuildMessageDeleteEvent(JDA JDA, Guild guild, TextChannel channel, long messageId) {
        super(JDA, guild, channel, messageId);
    }
}
