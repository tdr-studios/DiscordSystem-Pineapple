package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.modules.NewModule;
import de.dseelp.discordsystem.utils.config.JsonConfig;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.tdrstudios.utils.Branding;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
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

    @Getter
    @Setter
    private LogSystem logSystem;


    public void start() {
        if(BotConfig.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
            JsonConfig config;
            logSystem.error(" This Status is only for maintenance -> " + BotConfig.getOnlineStatus());
            File file = new File("config.json");
            config = JsonConfig.load(file);
            config.set("defaultOnlineStatus", "Online");
            config.save(file);
        }

        if (shardManager != null) {
            logSystem.write("Starting DiscordBot!");
            try {
                shardManager = DefaultShardManagerBuilder.createDefault(token)
                .build();
            } catch (Exception e) {
                logSystem.error("Can't start DiscordBot! "+e.getCause().getMessage());
                System.exit(0);
            }
            if(Discord.isMaintenance()){
                shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
            }
            setStatus(BotConfig.getOnlineStatus());
            setActivity(BotConfig.getActivityType(), BotConfig.getActivityName());

            shardManager.addEventListener(new ShardEventListener(Discord.getEventManager()));
            Discord.getEventManager().setCommandListener(Discord.getCommandSystem().getListener());
            logSystem.write(" ");
            logSystem.write("---------------------------------------");
            logSystem.write(Branding.Big1.getBranding());
            logSystem.write("---------------------------------------");
            logSystem.write(" ");
        }else {
            logSystem.error("Bot is already started!");
        }
    }

    public void setStatus(OnlineStatus status) {
        if(Discord.isMaintenance()) {
            logSystem.write(" ");
            logSystem.write("__________________________________________________");
            logSystem.write("[TDRStudios] The Api of your Bot is in maintenance!");
            logSystem.write("[TDRStudios] That can hold on for max 3 Weeks!");
            logSystem.write("__________________________________________________");
            logSystem.write(" ");
            shardManager.setStatus(OnlineStatus.DO_NOT_DISTURB);
            shardManager.setActivity(Activity.watching("Maintenance"));

        }
        if(status == OnlineStatus.UNKNOWN) {
            status = BotConfig.getOnlineStatus();
            if(status == OnlineStatus.DO_NOT_DISTURB) {
                status = OnlineStatus.ONLINE;
                logSystem.warning("A Service try to set the OnlineState to Maintenance!");
            }
        }else {
            //return;
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
        //shardManager = null;
        System.out.println(" ");
        System.out.println("---------------------------------------");
        System.out.println(Branding.Big1.getBranding());
        System.out.println("---------------------------------------");
        System.out.println("      Thanks for using our API");
    }
}
