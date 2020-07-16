package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.api.*;
import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.tdrstudios.utils.Branding;
import net.dv8tion.jda.api.OnlineStatus;

public class RestartCommand extends Command {


    public RestartCommand() {
        super(null, "Restarts the Application", CommandType.CONSOLE, "Restart", "rs");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        Discord.getBot().getShardManager().restart();

        Discord.getBot().setActivity(ActivityType.PLAYING, "Restarting...");
        System.out.println("[Restart] Restart!");
        ModuleService.reloadModules();
        DiscordSystemApplication.getContext().getBean(ModuleService.class).stop();
        System.out.println("[Restart] Modules are disabled!");
        System.out.println(" ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("[Restart] Try to Load Modules");
        DiscordSystemApplication.getContext().getBean(ModuleService.class).load();
        System.out.println("[Restart] Modules load!");
        System.out.println("[Restart] Try to enable Modules");
        DiscordSystemApplication.getContext().getBean(ModuleService.class).enableAll();
        System.out.println("[Restart] Complete");
        Discord.getBot().setStatus(OnlineStatus.UNKNOWN);

        Discord.getBot().setActivity(BotConfig.getActivityType(), BotConfig.getActivityName());
        System.out.println(" ");
        System.out.println("---------------------------------------");
        System.out.println(Branding.Big1.getBranding());
        System.out.println("---------------------------------------");
        System.out.println(" ");


    }
}
