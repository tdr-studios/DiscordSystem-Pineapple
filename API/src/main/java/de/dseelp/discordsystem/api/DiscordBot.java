package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.utils.config.JsonConfig;
import de.tdrstudios.utils.Branding;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.io.File;

public class DiscordBot {

    public DiscordBot() {

    }
    @Getter
    private ShardManager shardManager;
    public DiscordBot(String token) {
        this.token = token;
    }
    private String token;


    public void start() {
        if(BotConfig.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
            JsonConfig config;
            System.err.println("[TDRStudios] This Status is only for maintenance -> " + BotConfig.getOnlineStatus());
            File file = new File("config.json");
            config = JsonConfig.load(file);
            config.set("defaultOnlineStatus", "Online");
            config.save(file);

        }

        if (shardManager == null) {
            try {
                shardManager = DefaultShardManagerBuilder.createDefault(token)
                .build();
            } catch (Exception e) {
                System.out.println("[System] Can't Start discord bot!");
                System.exit(0);
            }
            if(Discord.isMaintenance()){
                shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
            }
            setStatus(BotConfig.getOnlineStatus());
            setActivity(BotConfig.getActivityType(), BotConfig.getActivityName());

            shardManager.addEventListener(new ShardEventListener(Discord.getEventManager()));
            Discord.getEventManager().setCommandListener(Discord.getCommandSystem().getListener());
            System.out.println(" ");
            System.out.println("---------------------------------------");
            System.out.println(Branding.Big1.getBranding());
            System.out.println("---------------------------------------");
            System.out.println(" ");

        }
    }

    public void setStatus(OnlineStatus status) {
        if(Discord.isMaintenance()) {
            System.out.println(" ");
            System.out.println("__________________________________________________");
            System.out.println("[TDRStudios] The Api of your Bot is in maintenance!");
            System.out.println("[TDRStudios] That can hold on for max 3 Weeks!");
            System.out.println("__________________________________________________");
            System.out.println(" ");
            shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
            shardManager.setActivity(Activity.watching("Maintenance"));

        }
        if(status.equals(OnlineStatus.UNKNOWN)) {
            status = BotConfig.getOnlineStatus();
        }
        shardManager.setStatus(status);
    }
    public OnlineStatus getOnlineStatus(int ID) {
        return OnlineStatus.UNKNOWN;
    }

    public void setActivity(ActivityType type, String activity) {
        assert type.toDiscordActivityType() != null;
        shardManager.setActivity(Activity.of(type.toDiscordActivityType(), activity));
        if(Discord.isMaintenance()) {
            shardManager.setActivity(Activity.watching("Maintenance"));

        }
    }


    public void stop() {
        if (shardManager == null) return;
        shardManager.setStatus(OnlineStatus.OFFLINE);
        shardManager.shutdown();
        System.out.println(" ");
        System.out.println("---------------------------------------");
        System.out.println(Branding.Big1.getBranding());
        System.out.println("---------------------------------------");
        System.out.println("      Thanks for using our API");
    }
}
