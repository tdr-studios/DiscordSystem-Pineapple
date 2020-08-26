package de.dseelp.discordsystem.api.modules;

import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;

import java.io.File;

public interface ModuleStartupHandler {
    void startup(ModuleLoader system, File folder);



}
