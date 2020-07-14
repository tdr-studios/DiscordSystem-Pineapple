package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.core.module.commands.console.StopCommand;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.commands.ConsoleCommandSender;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
@DependsOn("DiscordService")
public class ConsoleService {
    private Thread thread;
    private boolean started;
    private BufferedReader bufferedReader;
    @PostConstruct
    public void startReading() {
        System.out.println("Started ConsoleService");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                CommandSystem system = Discord.getCommandSystem();
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    while ((line = bufferedReader.readLine()) != null && started) {
                        // ... do something with line
                        system.execute(new ConsoleCommandSender(), system.parseCommand(line));
                    }
                } catch (IOException e) {
                    // ... handle IO exception
                }
            }
        });
        started = true;
        thread.start();
    }

    public void stop() {
        started = false;
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
