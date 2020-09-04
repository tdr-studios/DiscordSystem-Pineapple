package de.dseelp.discordsystem.utils.console.logging;

import java.util.HashMap;

public class LoggerRegistry {
    private static HashMap<String, LogSystem> systems = new HashMap<>();
    public static void register(String name, SystemLogger logger) {
        if (!systems.containsKey(name.toLowerCase())) {
            LogSystem logSystem = new LogSystem(logger);
            systems.put(name.toLowerCase(), logSystem);
        }
    }

    public static LogSystem get(String name) {
        return systems.get(name.toLowerCase());
    }

    public static LogSystem get() {
        return get("main");
    }
}
