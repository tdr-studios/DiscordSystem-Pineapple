package de.dseelp.discordsystem.core.spring.listeners;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.event.EventManager;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationContextInitialized implements ApplicationListener<ApplicationContextInitializedEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        BotConfig.load();
        Discord.setEventManager(new EventManager());
        Discord.setCommandSystem(new CommandSystem());
    }
}
