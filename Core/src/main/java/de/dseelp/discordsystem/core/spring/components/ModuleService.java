package de.dseelp.discordsystem.core.spring.components;

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

    @Getter
    private static final File moduleFolder = new File("modules");

    @PostConstruct
    public void load() {
        System.out.println("Load");
        loader = new NewModuleLoader();
        manager = new NewModuleManager(loader);
        if (!moduleFolder.exists()) moduleFolder.mkdirs();
        manager.loadFolder(moduleFolder);
    }

    @PostConstruct
    public void enableAll() {
        for (Module module : manager.getModules()) {
            module.setEnabled(true);
        }
        System.out.println("Enable");
    }

    public void stop() {
        for (Module module : manager.getModules()) {
            module.setEnabled(false);
        }
        manager.unloadAll();
    }
}
