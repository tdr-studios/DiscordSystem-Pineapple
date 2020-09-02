package de.dseelp.discordsystem.api.reactions;

import com.vdurmont.emoji.Emoji;
import net.dv8tion.jda.api.entities.Message;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ReactionUtils {
    public static void addReactionQueue(Emoji emoji, Message message) {
        message.addReaction(emoji.getUnicode()).queue();
    }

    public static void addReactionQueue(Emoji emoji, Message message, Consumer<? super Void> consumer) {
        message.addReaction(emoji.getUnicode()).queue(consumer);
    }

    public static CompletableFuture<Void> addReactionSubmit(Emoji emoji, Message message) {
        return message.addReaction(emoji.getUnicode()).submit();
    }
}
