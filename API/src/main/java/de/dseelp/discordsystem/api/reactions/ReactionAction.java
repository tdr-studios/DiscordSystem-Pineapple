package de.dseelp.discordsystem.api.reactions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionAction {
    private final String name;
    private final String description;
    private final Reaction reaction;
    private final SubmitAction submitAction;

    private ReactionAction(String name, String description, Reaction reaction, SubmitAction submitAction) {
        this.name = name;
        this.description = description;
        this.reaction = reaction;
        this.submitAction = submitAction;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String name;
        private String description;
        private Reaction reaction;
        private SubmitAction submitAction;

        public SubmitAction getSubmitAction() {
            return submitAction;
        }

        public Builder setSubmitAction(SubmitAction submitAction) {
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
