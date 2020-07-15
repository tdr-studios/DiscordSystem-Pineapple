package de.dseelp.discordsystem.api.commands;

import de.dseelp.discordsystem.api.events.discord.guild.GuildMessageReceivedEvent;
import lombok.Getter;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.RestAction;

public class DiscordGuildCommandSender implements CommandSender {
    @Getter
    private final GuildMessageReceivedEvent event;

    public DiscordGuildCommandSender(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    public User getAuthor() {
        return event.getAuthor();
    }

    public Guild getGuild() {
        return event.getGuild();
    }

    public Message getMessage() {
        return event.getMessage();
    }

    public TextChannel getChannel() {
        return event.getChannel();
    }

    @Override
    public void sendMessage(String message) {
        event.getChannel().sendMessage(message).queue();
    }

    @Override
    public RestAction<Message> sendMessage(Message message) {
        return event.getChannel().sendMessage(message);
    }

    @Override
    public RestAction<Message> sendMessage(MessageEmbed embed) {
        return event.getChannel().sendMessage(embed);
    }

    @Override
    public String getName() {
        return event.getAuthor().getName();
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return true;
    }
}
