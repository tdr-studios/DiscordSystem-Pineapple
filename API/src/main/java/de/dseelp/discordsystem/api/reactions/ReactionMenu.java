package de.dseelp.discordsystem.api.reactions;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.event.EventHandler;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.api.events.discord.guild.GuildMessageReactionAddEvent;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReactionMenu implements Listener {
    private List<ReactionAction> reactionActions = new ArrayList<>();

    public ReactionAction[] getReactionActions() {
        return reactionActions.toArray(new ReactionAction[reactionActions.size()]);
    }

    private ReactionMenu(DiscordModule module) {
        Discord.getEventManager().addListener(module, this);
    }

    public static ReactionMenu create(DiscordModule module) {
        return new ReactionMenu(module);
    }

    @Deprecated
    public void addReactionAction(ReactionAction action) {
        reactionActions.add(action);
    }

    private String cancelledMessage = "Cancelled";

    @Getter
    private Message message;

    public void sendQueue(TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);
        for (ReactionAction action : reactionActions) {
            builder.addField(action.getName(), action.getDescription() +" - "+action.getReaction().getAsUnicode(), false);
        }
        channel.sendMessage(builder.build()).queue(msg -> {
            message = msg;
            for (ReactionAction action : reactionActions) {
                msg.addReaction(action.getReaction().getAsUnicode()).queue();
            }
        });
    }

    public void cancel() {
        message.editMessage(new EmbedBuilder().setColor(Color.RED).setDescription(cancelledMessage).build()).queue();
        message.clearReactions().queue();
    }

    @EventHandler
    public void onReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getUser().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        MessageReaction reaction = event.getReaction();
        MessageReaction.ReactionEmote reactionEmote = reaction.getReactionEmote();
        if (reactionEmote.isEmote()) return;
        if (reaction.getMessageIdLong() == message.getIdLong()) {
            reaction.removeReaction(event.getUser()).queue();
            for (ReactionAction action : reactionActions) {
                if (action.getReaction().getAsUnicode().equals(reactionEmote.getEmoji())) {
                    action.getSubmitAction().accept(this);
                }
            }
        }
    }
}
