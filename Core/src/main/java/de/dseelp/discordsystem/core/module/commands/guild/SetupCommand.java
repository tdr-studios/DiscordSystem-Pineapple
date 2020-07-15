package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.GuildManager;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;

public class SetupCommand extends Command {

    public SetupCommand() {

        super(null, "Setup the Bot", CommandType.DISCORD_GUILD, "setup");

    }



    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordGuildCommandSender guildSender = (DiscordGuildCommandSender) sender;
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("AlertChannel")) {
                GuildConfig config = Discord.getGuildManager().getGuildConfig(((DiscordGuildCommandSender) sender).getGuild());
                TextChannel channel = guildSender.getChannel();
                String channelID = channel.getId();
                config.getDocument().add("AlertChannel", channelID);
            }

        }else {

            EmbedBuilder eb = EmbedUtils.createSuccessBuilder("Setup", " Setup your Bot! " + System.lineSeparator() + "You can choose between this:" + System.lineSeparator() + "AlertChannel - Set the Channel for Alerts and More");
            EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender)sender).getAuthor());
            EmbedUtils.setTimestamp(eb, Instant.now());
            sender.sendMessage(eb.build()).queue();

            //HelpSite
        }
    }
}
