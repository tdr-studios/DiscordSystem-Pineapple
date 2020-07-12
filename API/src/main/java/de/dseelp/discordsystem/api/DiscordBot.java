package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.EventManager;
import de.dseelp.discordsystem.utils.Listener;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private ShardManager shardManager;
    public DiscordBot(String token, EventManager eventManager) {
        this.token = token;
        this.eventManager = eventManager;
    }
    private String token;
    @Getter
    private final EventManager eventManager;

    public void start() {
        if (shardManager == null) {
            try {
                shardManager = DefaultShardManagerBuilder.createDefault(token)
                .build();
                shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
                shardManager.addEventListener(new ShardEventListener(eventManager));
            } catch (LoginException e) {
                e.printStackTrace();
            }
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
