package de.dseelp.discordsystem.modules.moderation.commands;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.modules.moderation.ModerationConfig;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BugReportCommand extends Command {

    public BugReportCommand() {
        super(null, "Creates a BugReport", CommandType.DISCORD_GUILD, "bugReport");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args, Command command) {
        DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;
        sender.getMessage().delete().queue();
        Guild guild = sender.getGuild();
        if (!ModerationConfig.getDocument(guild).has("bugReportChannel")) {
            sender.sendMessage(EmbedUtils.createError("Error","Please use the setup command first to setup the bugReportChannel!")).queue();
        }else if (args.length > 1) {
            StringBuilder builder = new StringBuilder();
            boolean first = true;
            for (String arg : Arrays.copyOfRange(args, 1, args.length)) {
                if (arg.endsWith(" ")) arg = arg.substring(0, arg.length() - 1);
                if (!first) builder.append(" ");
                builder.append(arg);
                first = false;
            }
            User author = sender.getAuthor();
            String avatarUrl = author.getAvatarUrl();
            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.YELLOW);
            eb.setThumbnail(avatarUrl);
            eb.addField("Area", args[0], false);
            eb.addField("Bug", builder.toString(), false);
            TextChannel textChannel = guild.getTextChannelById(ModerationConfig.getBugReportChannelId(guild));
            if (textChannel == null) {
                sender.sendMessage(EmbedUtils.createError("Error", "The Channel no longer exists!")).queue();
            }else {
                textChannel.sendMessage(eb.build()).queue();
            }
        }else sender.sendMessage(EmbedUtils.createError("Usage", "Please use: "+ BotConfig.getCommandPrefix() + "bugreport <Area> <Bug>")).queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));
    }
}