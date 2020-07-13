package de.dseelp.discordsystem.core.spring.listeners;

import de.dseelp.discordsystem.core.DiscordSystemApplication;
import de.dseelp.discordsystem.core.impl.DefaultGuildManager;
import de.dseelp.discordsystem.core.spring.event.AppFinishedStartedEvent;
import de.dseelp.discordsystem.api.Discord;
import org.springframework.context.ApplicationListener;

public class AppFinishedStartedListener implements ApplicationListener<AppFinishedStartedEvent> {

    @Override
    public void onApplicationEvent(AppFinishedStartedEvent event) {
        Discord.setGuildManager(DiscordSystemApplication.getContext().getBean(DefaultGuildManager.class));
    }
}
