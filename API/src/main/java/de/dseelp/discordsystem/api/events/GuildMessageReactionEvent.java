package de.dseelp.discordsystem.api.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GuildMessageReactionEvent extends GuildMessageEvent {

    private final Member issuer;
    private final MessageReaction reaction;
    private final long userId;

    public GuildMessageReactionEvent(JDA JDA, Guild guild, Member user, MessageReaction reaction, long userId) {
        super(JDA, guild, reaction.getTextChannel(), reaction.getMessageIdLong());
        this.issuer = user;
        this.reaction = reaction;
        this.userId = userId;
    }

    @Nonnull
    public String getUserId() {
        return Long.toUnsignedString(this.userId);
    }

    public long getUserIdLong() {
        return this.userId;
    }

    @Nullable
    public User getUser() {
        return this.issuer == null ? this.getJDA().getUserById(this.userId) : this.issuer.getUser();
    }

    @Nullable
    public Member getMember() {
        return this.issuer;
    }

    @Nonnull
    public MessageReaction getReaction() {
        return this.reaction;
    }

    @Nonnull
    public MessageReaction.ReactionEmote getReactionEmote() {
        return this.reaction.getReactionEmote();
    }
}
