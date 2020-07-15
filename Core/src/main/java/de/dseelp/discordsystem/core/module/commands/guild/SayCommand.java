package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import net.dv8tion.jda.api.EmbedBuilder;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class SayCommand extends Command {

    public SayCommand() {

        super(null, "Say Something as the Bot", CommandType.DISCORD_GUILD, "say");

    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordGuildCommandSender guildSender = (DiscordGuildCommandSender) sender;
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String arg : args) {
            if (!first) builder.append(", ");
            builder.append(arg);
            first = false;
        }
        EmbedBuilder eb = createNormalBuilder("Nachricht",   builder.toString());
        eb.setColor(1);
        eb.setFooter(guildSender.getAuthor().getName(), guildSender.getAuthor().getAvatarUrl());
        sender.sendMessage(eb.build()).queue();
        System.out.println("[SayCommand] A Massage was send by the Bot");
        System.out.println("[SayCommand] Sender: " + guildSender.getAuthor().getName() + " ID: " + guildSender.getAuthor().getAsTag());
        System.out.println("[SayCommand] Message: " + builder.toString());
    }
}
