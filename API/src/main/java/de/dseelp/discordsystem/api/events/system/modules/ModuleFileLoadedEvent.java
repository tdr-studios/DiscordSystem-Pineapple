package de.dseelp.discordsystem.api.events.system.modules;

import de.dseelp.discordsystem.api.event.Event;
import de.dseelp.discordsystem.api.modules.ModuleClassLoader;
import lombok.Getter;

public class ModuleFileLoadedEvent extends Event {
    @Getter
    private final ModuleClassLoader classLoader;

    public ModuleFileLoadedEvent(ModuleClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
