package de.dseelp.discordsystem.core.spring.components;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.core.module.commands.guild.TestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("DiscordService")
public class DiscordService {
    @Autowired
    private DiscordBot discordBot;

    @PostConstruct
    private void start() {
        Discord.setBot(discordBot);
        discordBot.start();
    }
}
