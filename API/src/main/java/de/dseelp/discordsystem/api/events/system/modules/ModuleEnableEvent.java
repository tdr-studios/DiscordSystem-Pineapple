package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import de.dseelp.discordsystem.api.modules.Module;
import lombok.Getter;

public class ModuleEnableEvent extends Event {
    @Getter
    private final Module module;

    public ModuleEnableEvent(Module module) {
        this.module = module;
    }
}
