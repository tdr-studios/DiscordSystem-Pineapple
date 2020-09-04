package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("DiscordService")
@DependsOn("ModuleService")
public class DiscordService {
    @Autowired
    private DiscordBot discordBot;

    @PostConstruct
    private void start() {
        LoggerRegistry.register("discord", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "Discord"));
        Discord.setBot(discordBot);
        discordBot.setLogSystem(LoggerRegistry.get("discord"));
        discordBot.start();
    }
}
