package de.dseelp.discordsystem.api.reload;

import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

public class ReloadableReloadEvent extends Event {
    @Getter
    private final Reloadable reloadable;

    public ReloadableReloadEvent(Reloadable reloadable) {
        this.reloadable = reloadable;
    }
}
