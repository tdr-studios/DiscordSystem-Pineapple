package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import de.dseelp.discordsystem.api.modules.ModuleClassLoader;
import lombok.Getter;

public class ModuleUnloadEvent extends Event {
    @Getter
    private final ModuleClassLoader classLoader;

    public ModuleUnloadEvent(ModuleClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
