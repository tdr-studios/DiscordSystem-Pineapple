package de.dseelp.discordsystem.api.events.discord.guild;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;

public class GuildMessageReactionAddEvent extends GuildMessageReactionEvent {

    public GuildMessageReactionAddEvent(JDA JDA, Guild guild, Member user, MessageReaction reaction, long userId) {
        super(JDA, guild, user, reaction, userId);
    }
}
