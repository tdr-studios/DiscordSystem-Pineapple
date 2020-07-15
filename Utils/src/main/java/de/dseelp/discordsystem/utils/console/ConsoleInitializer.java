package de.dseelp.netcloud.lib.console;

import de.dseelp.netcloud.lib.commands.CommandManager;
import de.dseelp.netcloud.lib.console.logging.LoggerRegistry;

public class ConsoleInitializer {
    private static boolean initialized = false;
    public static Console initialize(String prompt, String defaultLogger) {
        if (!initialized) {
            initialized = true;
            Console console = new Console();
            console.setPrompt(prompt);
            console.addReadHandler("cmdhandler",new CommandManager.CommandConsumer());
            console.init();
            ConsoleSystem.init(console);
            LoggerRegistry.register("main", ConsoleSystem.createLogger(defaultLogger));
            return console;
        }
        return null;
    }
}
