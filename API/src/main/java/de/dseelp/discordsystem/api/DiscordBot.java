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
            config.set("defaultOnlineStatus", "ONLINE");
            config.save(file);
        }

        if (shardManager == null) {
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
            logSystem.error("The Bot cant be startet!");
            logSystem.write("******************************************************");
            logSystem.error("Try to check if the Bot is alredy startet or the Token");
            logSystem.error("is invalide! ");
            logSystem.lineSeperator();
            logSystem.write("Token = " + BotConfig.getToken().toCharArray());
            logSystem.debug(" This is a Discord ore Config Error dont report to tdr-studios");
            logSystem.write("******************************************************");
            logSystem.lineSeperator();


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

    public void reload() {
        if (shardManager == null) return;
        logSystem.write("Reloading Bot...");
        setStatus(BotConfig.getOnlineStatus());
        setActivity(BotConfig.getActivityType(), BotConfig.getActivityName());
        logSystem.write("Reloaded Bot!");
    }
}
