package de.dseelp.discordsystem.api.modules;

import java.io.File;
import java.util.List;

public abstract class ModuleManager {
    protected final ModuleLoader loader;

    public ModuleManager(ModuleLoader loader) {
        this.loader = loader;
    }
    public abstract void loadFolder(File folder);
    public abstract void unloadAll();
    public abstract void load(File module);
    public abstract void enable(String name);
    public abstract void unload(File module);
    public abstract void unload(Module module);
    public abstract void unload(ModuleInfo module);
    public abstract void disable(String name);
    public abstract void disable(Module module);
    public abstract Module getModule(String name);
    public abstract List<Module> getModules();

    public ModuleManager getInstance() {
        return this;
    }
}
