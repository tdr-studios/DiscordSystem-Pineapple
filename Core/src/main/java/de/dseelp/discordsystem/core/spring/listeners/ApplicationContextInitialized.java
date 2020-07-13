package de.dseelp.discordsystem.core.spring.listeners;

import de.dseelp.discordsystem.api.BotConfig;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationContextInitialized implements ApplicationListener<ApplicationContextInitializedEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        BotConfig.load();
    }
}
