package de.tdrstudios.info.module.commands;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.tdrstudios.info.module.InformationModule;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class YTcommand extends Command {

    //LogSystem logSystem = loging.logsystem;
    LogSystem logSystem = InformationModule.getInstance().getLogSystem();

    public YTcommand() {
        super(null, "Show a YT-Channel", CommandType.DISCORD_GUILD, "yt","YouTube");
    }


    public void execute(CommandSender sender, String[] strings, Command command){
        GuildConfig gc = Discord.getGuildManager().getGuildConfig(((DiscordGuildCommandSender) sender).getGuild());
        String yt = gc.getDocument().getString("info_yt");
       // String yt = gc.setDocument();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription(yt);
        eb.setColor(Color.RED);
        eb.setTitle("YouTube");
        if(yt == null) {
            eb.setDescription("No Link!");
        }

        EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender)sender).getAuthor());
        EmbedUtils.setTimestamp(eb, Instant.now());


       // sender.sendMessage(eb.build());
        ((DiscordGuildCommandSender) sender).getChannel().sendMessage(eb.build()).queue();
        logSystem.write("Showing " + yt +" to " + sender.getName());

    }
}
