package de.dseelp.discordsystem.api.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.RestAction;

public class ConsoleCommandSender implements CommandSender {

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public RestAction<Message> sendMessage(Message message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RestAction<Message> sendMessage(MessageEmbed embed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return "ConsoleSender";
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return true;
    }
}
