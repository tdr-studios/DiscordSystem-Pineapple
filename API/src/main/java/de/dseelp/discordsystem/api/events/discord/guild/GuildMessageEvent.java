package de.dseelp.discordsystem.api.events.discord.guild;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class GuildMessageEvent extends GuildEvent {
    @Getter
    private final TextChannel channel;

    private final long messageId;

    private String getMessageId() {
        return Long.toUnsignedString(messageId);
    }

    private long getMessageIdLong() {
        return messageId;
    }

    public GuildMessageEvent(JDA JDA, Guild guild, TextChannel channel, long messageId) {
        super(JDA, guild);
        this.channel = channel;
        this.messageId = messageId;
    }
}
