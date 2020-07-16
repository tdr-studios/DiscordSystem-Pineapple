package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.commands.ConsoleCommandSender;
import de.dseelp.discordsystem.utils.console.Console;
import de.dseelp.discordsystem.utils.console.ConsoleInitializer;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import lombok.Getter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@DependsOn("DiscordService")
public class ConsoleService {
    public Console getConsole() {
        return console;
    }

    private Console console;
    //@PostConstruct
    public void start() {
        System.out.println("Creating");
        CommandSystem system = Discord.getCommandSystem();
        console = ConsoleInitializer.initialize("Console > ", "main");
        console.addReadHandler("commandHandler", new Consumer<String>() {
            @Override
            public void accept(String s) {
                system.execute(new ConsoleCommandSender(), system.parseCommand(s));
            }
        });
        System.out.println("Console: "+console);
        DiscordSystemApplication.setConsole(console);
        LoggerRegistry.register("normal", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "DiscordSystem"));
        LoggerRegistry.register("modules", ConsoleSystem.createSubLogger(LoggerRegistry.get("normal").getLogger(), "Modules"));
        LogSystem normalLogger = LoggerRegistry.get("normal");
        System.setOut(normalLogger.getOut());
        System.setErr(normalLogger.getError());
    }

    public void stop() {
        System.out.println(console);
        DiscordSystemApplication.getConsole().shutdown();
    }
}
