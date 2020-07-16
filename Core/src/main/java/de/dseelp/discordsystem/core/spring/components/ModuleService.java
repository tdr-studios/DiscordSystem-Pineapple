package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.core.module.RootModule;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.api.modules.ModuleLoader;
import de.dseelp.discordsystem.api.modules.ModuleManager;
import de.dseelp.discordsystem.core.impl.modules.NewModuleLoader;
import de.dseelp.discordsystem.core.impl.modules.NewModuleManager;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class ModuleService {
    @Getter
    private ModuleManager manager;
    @Getter
    private ModuleLoader loader;

    private RootModule rootModule;

    private LogSystem logSystem;

    public static void reloadModules() {
        //stop();
    }

    @Getter
    private static final File moduleFolder = new File("modules");

    public ModuleService() {
        logSystem = LoggerRegistry.get("modules");
        loader = new NewModuleLoader(DiscordModule.class);
        manager = new CustomModuleManager(loader);
    }

    @PostConstruct
    public void load() {
        rootModule = new RootModule();
        rootModule.setEnabled(true);
        if (!moduleFolder.exists()) moduleFolder.mkdirs();
        manager.loadFolder(moduleFolder);
    }

    @PostConstruct
    public void enableAll() {
        for (Module module : manager.getModules()) {
            module.setEnabled(true);
        }
    }

    public void stop() {
        rootModule.setEnabled(false);
        for (Module module : manager.getModules()) {
            module.setEnabled(false);
        }
        manager.unloadAll();
    }

    private static class CustomModuleManager extends NewModuleManager {

        public CustomModuleManager(ModuleLoader loader) {
            super(loader);
        }

        @Override
        public void disable(String name) {
            Module module = getModule(name);
            if (module instanceof DiscordModule) {
                Discord.getCommandSystem().removeCommandsForModule((DiscordModule) module);
            }
            super.disable(name);
        }
    }
}
