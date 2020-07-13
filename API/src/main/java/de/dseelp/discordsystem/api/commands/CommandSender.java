package de.dseelp.discordsystem.api.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.RestAction;

public interface CommandSender {
    void sendMessage(String message);
    RestAction<Message> sendMessage(Message message);
    RestAction<Message> sendMessage(MessageEmbed embed);
    String getName();

    boolean hasPermission(Permission permission);
}
