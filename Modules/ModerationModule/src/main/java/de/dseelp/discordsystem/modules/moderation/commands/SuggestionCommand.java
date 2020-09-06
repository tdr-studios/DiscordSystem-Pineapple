package de.dseelp.discordsystem.modules.moderation.commands;

import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;

public class SuggestionCommand extends Command {

    public SuggestionCommand() {
        super(null, "Creates a Suggestion", CommandType.DISCORD_GUILD, "suggestion");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args, Command command) {
        DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String arg : args) {
            if (arg.endsWith(" ")) arg = arg.substring(0, arg.length() - 1);
            if (!first) builder.append(" ");
            builder.append(arg);
            first = false;
        }
        User author = sender.getAuthor();
        String avatarUrl = author.getAvatarUrl();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.YELLOW);
        eb.setTimestamp(Instant.now());
        eb.setThumbnail(avatarUrl);
        eb.setFooter(author.getAsTag(), avatarUrl);
    }
}