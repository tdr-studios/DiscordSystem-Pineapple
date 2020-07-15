package de.dseelp.discordsystem.utils.console;

import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;

public class ConsoleInitializer {
    private static boolean initialized = false;
    public static Console initialize(String prompt, String defaultLogger) {
        if (!initialized) {
            initialized = true;
            Console console = new Console();
            console.setPrompt(prompt);
            console.init();
            ConsoleSystem.init(console);
            LoggerRegistry.register("main", ConsoleSystem.createLogger(defaultLogger));
            return console;
        }
        return null;
    }
}
