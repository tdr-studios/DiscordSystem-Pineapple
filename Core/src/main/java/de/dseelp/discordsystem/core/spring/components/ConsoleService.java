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
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@DependsOn("ModuleService")
public class ConsoleService {
    public Console getConsole() {
        return console;
    }

    private Console console;

    //@PostConstruct
    public void start() {
        System.out.println("Creating");
        console = ConsoleInitializer.initialize("Console > ", "main");
<<<<<<< HEAD
        console.addReadHandler("commandHandler", new Consumer<String>() {
            @Override
            public void accept(String s) {
                system.execute(new ConsoleCommandSender(), system.parseCommand(s));
            }
        });
        LoggerRegistry.register("normal", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "System"));
        LogSystem normalLogger = LoggerRegistry.get("normal");
        System.setOut(normalLogger.getOut());
        System.setErr(normalLogger.getError());

=======
        LoggerRegistry.register("normal", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "DiscordSystem"));
        LoggerRegistry.register("modules", ConsoleSystem.createSubLogger(LoggerRegistry.get("normal").getLogger(), "Modules"));
        LogSystem normalLogger = LoggerRegistry.get("normal");
        System.setOut(normalLogger.getOut());
        System.setErr(normalLogger.getError());
        Discord.setCommandSystem(new CommandSystem());
>>>>>>> 5a1be0de0753e1ef0fdbf29190b15f387b0cdfd6
    }

    public void stop() {
        ConsoleSystem.getConsole().shutdown();
    }






}
