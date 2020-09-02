package de.dseelp.discordsystem.api.reactions;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@Setter
public class ReactionAction {
    private final String name;
    private final String description;
    private final Reaction reaction;
    private final Consumer<ReactionMenu> submitAction;

    private ReactionAction(String name, String description, Reaction reaction, Consumer<ReactionMenu> submitAction) {
        this.name = name;
        this.description = description;
        this.reaction = reaction;
        this.submitAction = submitAction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private Reaction reaction;
        private Consumer<ReactionMenu> submitAction;

        public Consumer<ReactionMenu> getSubmitAction() {
            return submitAction;
        }

        public Builder setSubmitAction(Consumer<ReactionMenu> submitAction) {
            this.submitAction = submitAction;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setReaction(Reaction emoji) {
            this.reaction = emoji;
            return this;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Reaction getReaction() {
            return reaction;
        }

        public ReactionAction build() {
            return new ReactionAction(name, description, reaction, submitAction);
        }
    }
}
