package de.dseelp.discordsystem.api.reactions;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.function.Consumer;

@Getter
@Setter
public class SubmitAction {
    public SubmitAction() {}
    public SubmitAction(MessageEmbed newEmbed, Consumer<ReactionMenu> execute) {
        this.newEmbed = newEmbed;
        this.execute = execute;
    }

    private MessageEmbed newEmbed;

    public SubmitAction(Consumer<ReactionMenu> execute) {
        this.execute = execute;
    }

    public SubmitAction(MessageEmbed newEmbed) {
        this.newEmbed = newEmbed;
    }

    private Consumer<ReactionMenu> execute;
}
