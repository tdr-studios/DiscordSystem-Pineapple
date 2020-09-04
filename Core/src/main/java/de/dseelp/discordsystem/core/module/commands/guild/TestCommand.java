package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;

import static de.dseelp.discordsystem.api.EmbedUtils.createNormalBuilder;

public class TestCommand extends Command {

    public TestCommand() {
        super(new RolePermission("testcommand"), "Test", CommandType.DISCORD_GUILD, "test");
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
        EmbedBuilder eb = createNormalBuilder("TestCommand", "Der TestCommand wurde mit den Argumenten: { "+ builder.toString() +" } ausgeführt!");
        eb.setFooter(guildSender.getAuthor().getName(), guildSender.getAuthor().getAvatarUrl());
        sender.sendMessage(eb.build()).queue();
    }
}
