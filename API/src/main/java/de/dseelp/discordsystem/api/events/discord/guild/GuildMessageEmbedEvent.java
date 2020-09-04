package de.dseelp.discordsystem.api.events.discord.guild;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class GuildMessageEmbedEvent extends GuildMessageEvent {

    @Getter
    private final List<MessageEmbed> embeds;

    public GuildMessageEmbedEvent(JDA JDA, Guild guild, TextChannel channel, long messageId, List<MessageEmbed> embeds) {
        super(JDA, guild, channel, messageId);
        this.embeds = embeds;
    }
}
