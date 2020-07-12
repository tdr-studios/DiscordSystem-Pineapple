package de.dseelp.discordsystem.api.events;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

public class GuildMessageReceivedEvent extends GuildMessageEvent {

    @Getter
    private final Message message;

    public GuildMessageReceivedEvent(JDA JDA, Guild guild, Message message) {
        super(JDA, guild, message.getTextChannel(), message.getIdLong());
        this.message = message;
    }

    public boolean isWebhookMessage() {
        return getMessage().isWebhookMessage();
    }

    public User getAuthor() {
        return message.getAuthor();
    }

    public Member getMember() {
        return message.getMember();
    }
}
