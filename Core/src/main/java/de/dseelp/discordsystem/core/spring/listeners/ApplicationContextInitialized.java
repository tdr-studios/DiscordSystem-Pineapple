package de.dseelp.discordsystem.core.spring.listeners;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.event.EventManager;
import de.dseelp.discordsystem.core.spring.components.ConsoleService;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

public class ApplicationContextInitialized implements ApplicationListener<ApplicationContextInitializedEvent> {

    @Bean
    public ConsoleService getService() {
        return service;
    }

    private ConsoleService service;

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        BotConfig.load();
        Discord.setEventManager(new EventManager());
        Discord.setCommandSystem(new CommandSystem());
        service = new ConsoleService();
        service.start();
    }
}
