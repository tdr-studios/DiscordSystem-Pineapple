package de.dseelp.discordsystem.modules.moderation.setups;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.api.setup.Setup;
import de.dseelp.discordsystem.modules.moderation.ModerationConfig;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class BugReportSetup implements Setup {
    @Override
    public void setup(CommandSender commandSender, String[] args) {
        DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;
        List<TextChannel> mentionedChannels = sender.getEvent().getMessage().getMentionedChannels();
        if (mentionedChannels.size() == 0) {
            sender.sendMessage(EmbedUtils.createError("Setup Error", "Please mention a Channel for bugreports")).queue();
        }else {
            if (mentionedChannels.size() > 1) {
                sender.sendMessage(EmbedUtils.createError("Setup Error", "Please mention only one Channel")).queue();
            }else {
                TextChannel textChannel = mentionedChannels.get(0);
                ModerationConfig.setBugReportChannelId(sender.getGuild(), textChannel.getIdLong());
                sender.sendMessage(EmbedUtils.createSuccess("Bugreportchannel set!", "The bugreport Channel was set to "+ textChannel.getAsMention()+"!")).queue();
            }
        }
    }

    @Override
    public String getName() {
        return "bugReportchannel";
    }

    @Override
    public String getDescription() {
        return "Sets the channel where Suggestions are send";
    }
}
