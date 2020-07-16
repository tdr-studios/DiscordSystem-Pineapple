package de.dseelp.discordsystem.api.reload;

import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

public class ReloadableReloadedEvent extends Event {
    @Getter
    private final Reloadable reloadable;

    public ReloadableReloadedEvent(Reloadable reloadable) {
        this.reloadable = reloadable;
    }
}
