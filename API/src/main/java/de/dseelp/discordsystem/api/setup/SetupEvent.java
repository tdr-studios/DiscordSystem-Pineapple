package de.dseelp.discordsystem.api.setup;

import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

public class SetupEvent extends Event {
    @Getter
    private final Setup setup;

    public SetupEvent(Setup setup) {
        this.setup = setup;
    }
}
