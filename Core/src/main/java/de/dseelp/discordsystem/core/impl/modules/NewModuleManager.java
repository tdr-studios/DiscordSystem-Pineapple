package de.dseelp.discordsystem.core.impl.modules;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.event.EventManager;
import de.dseelp.discordsystem.api.events.system.modules.*;
import de.dseelp.discordsystem.api.modules.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewModuleManager extends ModuleManager {

    public NewModuleManager(ModuleLoader loader) {
        super(loader);
    }
    protected List<ModuleClassLoader> loaders = new ArrayList<>();
    private EventManager eventManager = Discord.getEventManager();


    @Override
    public void loadFolder(File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar")))) {
            load(file);
        }
        for (ModuleClassLoader classLoader : loaders) {
            finalLoad(classLoader);
        }
    }

    @Override
    public void unloadAll() {
        for (ModuleClassLoader classLoader : loaders) {
            eventManager.callEvent(new ModuleUnloadEvent(classLoader));
            loader.unload(classLoader);
            eventManager.callEvent(new ModuleUnloadFinishedEvent(classLoader.getInfo()));
        }
        loaders.clear();
    }

    public void finalLoad(ModuleClassLoader classLoader) {
        eventManager.callEvent(new ModuleLoadEvent(classLoader));
        loader.load(classLoader);
        eventManager.callEvent(new ModuleLoadFinishedEvent(classLoader));
    }

    @Override
    public void load(File file) {
        if (file.isDirectory()) return;
        eventManager.callEvent(new ModuleFileLoadEvent(file));
        ModuleInfo info = loader.loadModuleInfo(file);
        if (info == null) {
            eventManager.callEvent(new ModuleLoadFailureEvent(file, "No Annotation found!"));
            return;
        }
        ModuleClassLoader classLoader = new ModuleClassLoader(file, info);
        if (!containsClassloader(classLoader)) {
            eventManager.callEvent(new ModuleFileLoadedEvent(classLoader));
            loaders.add(classLoader);
        }else {
            eventManager.callEvent(new ModuleLoadFailureEvent(file, "Cannot load two modules with the same name!"));
        }
    }

    private boolean containsClassloader(ModuleClassLoader loader) {
        for (ModuleClassLoader classLoader : loaders) {
            if (classLoader.getInfo().getFile().equals(loader.getInfo().getFile())) return true;
        }
        return false;
    }

    @Override
    public void enable(String name) {
        Module module = getModule(name);
        eventManager.callEvent(new ModuleEnableEvent(module));
        module.setEnabled(true);
        eventManager.callEvent(new ModuleEnableFinishedEvent(module));
    }

    @Override
    public void unload(File module) {
        unload(getModuleClassLoader(module));
    }

    public void unload(ModuleClassLoader classLoader) {
        loaders.remove(classLoader);
        if (classLoader != null) loader.unload(classLoader);
    }

    @Override
    public void unload(Module module) {
        unload(getModuleClassLoader(module));
    }

    @Override
    public void unload(ModuleInfo module) {
        unload(getModuleClassLoader(module));
    }

    @Override
    public void disable(String name) {
        Module module = getModule(name);
        disable(module);
    }

    public void disable(Module module) {
        eventManager.callEvent(new ModuleDisableEvent(module));
        module.setEnabled(false);
        eventManager.callEvent(new ModuleDisableFinishedEvent(module));
    }

    @Override
    public Module getModule(String name) {
        name = name.toLowerCase();
        for (ModuleClassLoader classLoader : loaders) {
            if (classLoader.getInfo().getName().toLowerCase().equals(name)) return classLoader.getModule();
        }
        return null;
    }

    @Override
    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        for (ModuleClassLoader classLoader : loaders) {
            modules.add(classLoader.getModule());
        }
        return modules;
    }

    public ModuleClassLoader getModuleClassLoader(String moduleName) {
        moduleName = moduleName.toLowerCase();
        for (ModuleClassLoader classLoader : loaders) {
            if (classLoader.getInfo().getName().toLowerCase().equals(moduleName)) return classLoader;
        }
        return null;
    }

    public ModuleClassLoader getModuleClassLoader(ModuleInfo info) {
        for (ModuleClassLoader classLoader : loaders) {
            if (classLoader.getInfo().getName().toLowerCase().equals(info.getName().toLowerCase())) return classLoader;
        }
        return null;
    }

    public ModuleClassLoader getModuleClassLoader(File file) {
        for (ModuleClassLoader classLoader : loaders) {
            if (classLoader.getInfo().getFile().equals(file)) return classLoader;
        }
        return null;
    }

    public ModuleClassLoader getModuleClassLoader(Module module) {
        for (ModuleClassLoader classLoader : loaders) {
            Module module1 = classLoader.getModule();
            if (module1 == null) continue;
            if (module1.getClass().equals(module.getClass())) return classLoader;
        }
        return null;
    }
}
