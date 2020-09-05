package de.dseelp.discordsystem.core.module.commands.guild.config;

import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLOutput;


//DE: Dieser Command ist später teil des +setup! Darf jederzeit umgewandelt werden! ~TDR
public class setAlertChannel extends Command {

    public setAlertChannel(Permission permission, String description, CommandType type, String... names) {
        super(new NullPermission(), "Set a new Alert Channel", CommandType.DISCORD_GUILD, "setAlertChannel", "setAC");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {


        //Work in Progress
        //Target: Set the Channel of the Command to the New Alert Channel in the SQLite!
        //Output in Console <



        GuildConfig gc = new GuildConfig();

        System.out.println("[Bot] " + sender.getName() + " set the AlertChannel on " + "%GUILD% " + "to " + "%Channel");

    }



}
