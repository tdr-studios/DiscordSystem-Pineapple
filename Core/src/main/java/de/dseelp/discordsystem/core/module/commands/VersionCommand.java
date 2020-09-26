package de.dseelp.discordsystem.core.module.commands;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import de.dseelp.discordsystem.api.modules.ModuleManager;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.io.File;
import java.sql.SQLOutput;
import java.util.List;

public class VersionCommand extends Command {
    public VersionCommand() {
    super(null, "Details about the Bot", CommandType.GUILD_AND_CONSOLE, "version", "v" , "about");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        if(sender instanceof DiscordGuildCommandSender)
        {
            ModuleService ms = new ModuleService();






            EmbedBuilder eb  = new EmbedBuilder();
            eb.setTitle("Bot-info");
            eb.addField("Version" , Discord.getVersion() , false);
            eb.addField("Modules" ,  /*manager.getModules().toString()*/ "Soon" , false);
            eb.addField("GitHub" , "https://github.com/tdr-studios/DiscordSystem", false);
            eb.setColor(Color.GREEN);
            sender.sendMessage(eb.build()).queue();


        }else {
            System.out.println("----------------------------[Bot-info]----------------------------");
            System.out.println("     Version > " + Discord.getVersion());
            System.out.println("     Modules > " + "Soon");
            System.out.println("Maintenance  > " + Discord.isMaintenance());
            System.out.println("      GitHub > " + "https://github.com/tdr-studios/DiscordSystem");
            System.out.println("------------------------------------------------------------------");
        }
    }
}
