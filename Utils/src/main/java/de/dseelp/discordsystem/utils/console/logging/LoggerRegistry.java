package de.dseelp.netcloud.lib.console.logging;

import java.util.HashMap;

public class LoggerRegistry {
    private static HashMap<String, LogSystem> systems = new HashMap<>();
    public static void register(String name, SystemLogger logger) {
        if (!systems.containsKey(name)) systems.put(name.toLowerCase(), new LogSystem(logger));
    }

    public static LogSystem get(String name) {
        return systems.get(name.toLowerCase());
    }

    public static LogSystem get() {
        return get("main");
    }
}
