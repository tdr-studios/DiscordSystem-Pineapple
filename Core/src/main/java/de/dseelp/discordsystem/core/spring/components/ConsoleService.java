package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.utils.console.Console;
import de.dseelp.discordsystem.utils.console.ConsoleColor;
import de.dseelp.discordsystem.utils.console.ConsoleInitializer;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import de.tdrstudios.api.DevTools.DevTools;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Component;

@Component
//@DependsOn("ModuleService")
public class ConsoleService {
    public Console getConsole() {
        return console;
    }

    private Console console;

    //@PostConstruct
    public void start() {
        console = ConsoleInitializer.initialize(ConsoleColor.RED+System.getProperty("user.name")+ConsoleColor.DEFAULT+"@"+Discord.getVersion()+" => ", "main");
        LoggerRegistry.register("bot", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "DiscordSystem"));
        LoggerRegistry.register("modules", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "Modules"));
        LoggerRegistry.register("devtools", ConsoleSystem.createLogger("DevTools"));
        LogSystem normalLogger = LoggerRegistry.get("main");
        System.setOut(normalLogger.getOut());
        System.setErr(normalLogger.getError());
        Discord.setCommandSystem(new CommandSystem());
        DevTools.initLog();
    }

    public void stop() {
        ConsoleSystem.getConsole().shutdown();
    }






}
