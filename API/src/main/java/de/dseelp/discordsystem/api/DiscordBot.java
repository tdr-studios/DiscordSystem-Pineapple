package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.utils.EventManager;
import de.dseelp.discordsystem.utils.Listener;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class DiscordBot {
    private ShardManager shardManager;
    public DiscordBot(String token, EventManager eventManager, CommandSystem commandSystem) {
        this.token = token;
        this.eventManager = eventManager;
        this.commandSystem = commandSystem;
    }
    private String token;
    @Getter
    private final EventManager eventManager;

    @Getter
    private final CommandSystem commandSystem;

    public void start() {
        if (shardManager != null) {
            try {
                shardManager = DefaultShardManagerBuilder.createDefault(token)
                .build();
            } catch (Exception e) {
                System.out.println("Can't Start discord bot!");
                System.exit(0);
            }
            shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
            shardManager.addEventListener(new ShardEventListener(eventManager));
            eventManager.addListener(commandSystem.getListener());
        }
    }

    public void stop() {
        if (shardManager == null) return;
        shardManager.setStatus(OnlineStatus.OFFLINE);
        shardManager.shutdown();
    }

    public void addListener(DiscordModule module, Listener listener) {
        eventManager.addListener(listener);
    }
}
