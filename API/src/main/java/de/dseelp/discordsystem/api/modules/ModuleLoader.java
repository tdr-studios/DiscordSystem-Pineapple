package de.dseelp.discordsystem.api.modules;

import java.io.File;

public interface ModuleLoader {

    ModuleInfo loadModuleInfo(File file);

    void load(ModuleClassLoader info);

    void enable(Module module);

    void disable(Module module);

    void unload(ModuleClassLoader info);
}
