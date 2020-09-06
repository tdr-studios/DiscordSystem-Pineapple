package de.tdrstudios.info.module.commands;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class YTcommand extends Command {

    LogSystem logSystem = loging.logsystem;

    public YTcommand() {
        super(null, "Show a YT-Channel", CommandType.DISCORD_GUILD, "yt","YouTube");
    }


    public void execute(CommandSender sender, String[] strings, Command command){
        GuildConfig gc = Discord.getGuildManager().getGuildConfig(((DiscordGuildCommandSender) sender).getGuild());
        String yt = gc.getDocument().getString("infomodule/yt");
        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription(yt);
        eb.setColor(Color.RED);
        eb.setTitle("YouTube");


        sender.sendMessage(eb.build());
        logSystem.write("Showing YouTube to " + sender.getName());

    }
}
