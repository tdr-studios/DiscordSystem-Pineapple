package de.dseelp.discordsystem.api.commands;

import de.dseelp.discordsystem.api.events.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.RestAction;

public class DiscordGuildCommandSender implements CommandSender {
    private final GuildMessageReceivedEvent event;

    public DiscordGuildCommandSender(GuildMessageReceivedEvent event) {
        this.event = event;
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
