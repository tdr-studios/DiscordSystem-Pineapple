package de.dseelp.discordsystem.api.reactions;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.events.discord.guild.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReactionMenu {
    private List<ReactionAction> reactionActions = new ArrayList<>();

    public ReactionAction[] getReactionActions() {
        return reactionActions.toArray(new ReactionAction[reactionActions.size()]);
    }

    private String cancelledMessage;

    private Message message;

    public void sendQueue(TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);
        for (ReactionAction actions : reactionActions) {
            builder.addField(actions.getName(), actions.getDescription(), false);
        }
        channel.sendMessage(builder.build());
    }

    public void cancel() {
        message.editMessage(new EmbedBuilder().setColor(Color.RED).setDescription(cancelledMessage).build()).queue();
    }

    public void onReactionAdd(GuildMessageReactionAddEvent event) {

    }
}
