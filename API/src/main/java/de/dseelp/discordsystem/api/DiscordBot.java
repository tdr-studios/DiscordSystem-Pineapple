package de.dseelp.discordsystem.api;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class DiscordBot {

    public DiscordBot() {

    }
    private static ShardManager shardManager;
    public DiscordBot(String token) {
        this.token = token;
    }
    private String token;


    public void start() {
        if (shardManager == null) {
            try {
                shardManager = DefaultShardManagerBuilder.createDefault(token)
                .build();
            } catch (Exception e) {
                System.out.println("Can't Start discord bot!");
                System.exit(0);
            }
            setStatus(BotConfig.getOnlineStatus());
            setActivity(BotConfig.getActivityType(), BotConfig.getActivityName());
            shardManager.addEventListener(new ShardEventListener(Discord.getEventManager()));
            Discord.getEventManager().setCommandListener(Discord.getCommandSystem().getListener());

        }
    }

    public void setStatus(OnlineStatus status) {
        shardManager.setStatus(status);
    }

    public void setActivity(ActivityType type, String activity) {
        assert type.toDiscordActivityType() != null;
        shardManager.setActivity(Activity.of(type.toDiscordActivityType(), activity));
    }

    public void stop() {
        if (shardManager == null) return;
        shardManager.setStatus(OnlineStatus.OFFLINE);
        shardManager.shutdown();
    }

    public static ShardManager getShardManager() {
      //  return this.shardManager
        return shardManager;
    }
}
