package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.events.GuildMessageReceivedEvent;
import de.dseelp.event.Event;
import de.dseelp.event.EventListener;
import de.dseelp.event.EventManager;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        DiscordBot bot = new DiscordBot("NzMxNTAwMTkyNDY5MjIxNDE2.XwnBqg.7C7p0IgejkAFkhoO87xx7zbeUps", new EventManager());
        bot.start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                bot.stop();
            }
        }));
        bot.addListener((EventListener<GuildMessageReceivedEvent>) event -> System.out.println(event.getAuthor().getName()+" -> "+ event.getMessage().getContentRaw()));
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // ... do something with line
                if (line.toLowerCase().equals("stop")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            // ... handle IO exception
        }
    }
}
