package de.dseelp.discordsystem.core.spring.listeners;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.api.event.EventManager;
import de.dseelp.discordsystem.api.reload.ReloadManager;
import de.dseelp.discordsystem.core.spring.components.ConsoleService;
import de.dseelp.discordsystem.utils.GsonUtils;
import de.dseelp.discordsystem.version.Serializer;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
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
        GsonUtils.addAdapter(DefaultArtifactVersion.class, new Serializer());
        Discord.setEventManager(new EventManager());
        Discord.setReloadManager(new ReloadManager());
        service = new ConsoleService();
        service.start();


    }
}
