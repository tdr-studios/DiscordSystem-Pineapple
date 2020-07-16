package de.dseelp.discordsystem.core.impl.modules;

import de.dseelp.discordsystem.api.modules.ModuleLoader;
import de.dseelp.discordsystem.api.modules.ModuleStartupHandler;

import java.io.File;

public class SimpleModuleStartupHandler implements ModuleStartupHandler {

    @Override
    public void startup(ModuleLoader system, File folder) {
        /*if (folder.isFile()) return;
        Arrays.asList(folder.listFiles()).forEach(system::load);
        Collection<Module> modules = system.getModules();
        for (Module module : modules) {
            system.enable(module);
        }

         */
    }
}
