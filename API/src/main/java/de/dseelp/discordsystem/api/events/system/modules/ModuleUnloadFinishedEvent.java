package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import lombok.Getter;

public class ModuleUnloadFinishedEvent extends Event {
    @Getter
    private final ModuleInfo moduleInfo;

    public ModuleUnloadFinishedEvent(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }
}
