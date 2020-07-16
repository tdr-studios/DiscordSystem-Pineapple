package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

import java.io.File;

public class ModuleLoadFailureEvent extends Event {
    @Getter
    private final File file;
    @Getter
    private final String message;

    public ModuleLoadFailureEvent(File file, String message) {
        this.file = file;
        this.message = message;
    }
}
