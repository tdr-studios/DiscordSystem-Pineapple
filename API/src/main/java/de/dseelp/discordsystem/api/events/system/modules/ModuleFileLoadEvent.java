package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

import java.io.File;

public class ModuleFileLoadEvent extends Event {
    @Getter
    private final File file;

    public ModuleFileLoadEvent(File file) {
        this.file = file;
    }
}
