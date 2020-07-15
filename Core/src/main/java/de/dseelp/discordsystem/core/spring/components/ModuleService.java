package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.core.module.RootModule;
import de.dseelp.modules.Module;
import de.dseelp.modules.ModuleLoader;
import de.dseelp.modules.ModuleManager;
import de.dseelp.modules.impl.NewModuleLoader;
import de.dseelp.modules.impl.NewModuleManager;
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

    public static void reloadModules() {
        //stop();
    }

    @Getter
    private static final File moduleFolder = new File("modules");

    public ModuleService() {
        loader = new NewModuleLoader();
        manager = new CustomModuleManager(loader);
    }

    @PostConstruct
    public void load() {
        System.out.println("[Module  Service] Load");
        if (!moduleFolder.exists()) moduleFolder.mkdirs();
        manager.loadFolder(moduleFolder);
    }

    @PostConstruct
    public void enableAll() {
        rootModule = new RootModule();
        for (Module module : manager.getModules()) {
            module.setEnabled(true);
        }
        System.out.println("[Module  Service] Enable");
        rootModule.setEnabled(true);
    }

    public void stop() {
        rootModule.setEnabled(false);
        for (Module module : manager.getModules()) {
            module.setEnabled(false);
        }
        manager.unloadAll();
    }

    private class CustomModuleManager extends NewModuleManager {

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
