package de.dseelp.discordsystem.modules.moderation.commands;

import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ClearCommand extends Command {

    public ClearCommand() {
        super(new RolePermission("clearCommand"), "Deletes a given number of messages", CommandType.DISCORD_GUILD, "clear");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args, Command command) {
        DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;
        if (args.length == 0) {
            sender.getMessage().delete().queue();
            sender.sendMessage(EmbedUtils.createError("Usage", "Please provide a number of messages that should be deleted")).queue(msg -> msg.delete().queueAfter(5,TimeUnit.SECONDS));
            return;
        }
        int msgs;
        try {
            msgs = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.getMessage().delete().queue();
            sender.sendMessage(EmbedUtils.createError("Usage", "Please provide a number of messages that should be deleted")).queue();
            return;
        }
        if (msgs > 1000) {
            sender.getMessage().delete().queue();
            sender.sendMessage(EmbedUtils.createError("Too much messages", "You can only delete 1000 messages at once!")).queue();
            return;
        }
        if (msgs < 1) {
            sender.getMessage().delete().queue();
            sender.sendMessage(EmbedUtils.createError("Too less messages", "You need to delete 1 message at once!")).queue();
            return;
        }
        int nmsgs = msgs;
        if (msgs == 1000) nmsgs = 999;
        TextChannel channel = sender.getChannel();
        getMessages(channel, nmsgs, messages -> {
            channel.purgeMessagesById(ArrayUtils.toPrimitive(messages));
            sender.sendMessage(EmbedUtils.createSuccess("Clear successful", "Deleted "+msgs+" messages in "+ channel.getAsMention())).queue(msg -> msg.delete().queueAfter(5,TimeUnit.SECONDS));
        });
    }


    public void getMessages(MessageChannel channel, int messageCount, Consumer<Long[]> callback) {
        if (messageCount > 1000) return;
        List<Long> messages = new ArrayList<>(messageCount);
        channel.getIterableHistory().cache(false).forEachAsync((message) ->
        {
            messages.add(message.getIdLong());
            return messages.size() < messageCount;
        }).thenRun(() -> {
            Long[] ids = messages.toArray(new Long[messages.size()]);
            System.gc();
            callback.accept(ids);
        });
    }
}