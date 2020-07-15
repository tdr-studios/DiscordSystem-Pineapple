package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.core.module.commands.console.StopCommand;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.commands.ConsoleCommandSender;
import de.dseelp.discordsystem.utils.console.ActionOutputStream;
import de.dseelp.discordsystem.utils.console.Console;
import de.dseelp.discordsystem.utils.console.ConsoleInitializer;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.PrintStream;
import java.util.function.Consumer;

@Component
@DependsOn("DiscordService")
public class ConsoleService {
    private Console console;
    //@PostConstruct
    public void start() {
        CommandSystem system = Discord.getCommandSystem();
        console = ConsoleInitializer.initialize("bot> ", "main");
        console.addReadHandler("commandHandler", new Consumer<String>() {
            @Override
            public void accept(String s) {
                system.execute(new ConsoleCommandSender(), system.parseCommand(s));
            }
        });
        LoggerRegistry.register("normal", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "DiscordSystem"));
        LogSystem normalLogger = LoggerRegistry.get("normal");
        System.setOut(normalLogger.getOut());
        System.setErr(normalLogger.getError());
    }

    public void stop() {
        console.shutdown();
    }
}
