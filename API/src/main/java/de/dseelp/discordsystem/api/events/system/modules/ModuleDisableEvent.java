package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import de.dseelp.discordsystem.api.modules.Module;
import lombok.Getter;

public class ModuleDisableEvent extends Event {
    @Getter
    private final Module module;

    public ModuleDisableEvent(Module module) {
        this.module = module;
    }
}
