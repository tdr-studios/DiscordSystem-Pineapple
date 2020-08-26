package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class SayCommand extends Command {

    public SayCommand() {

        super(new RolePermission("saycommand"), "Say Something as the Bot", CommandType.DISCORD_GUILD, "say");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordGuildCommandSender guildSender = (DiscordGuildCommandSender) sender;
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String arg : args) {
           if (!first)  builder.append(" ");
            builder.append(arg);
            first = false;
        }
        EmbedBuilder eb = createNormalBuilder("Message",   builder.toString());

        eb.setFooter(guildSender.getAuthor().getName(), guildSender.getAuthor().getAvatarUrl());
        sender.sendMessage(eb.build()).queue();
    }
}
