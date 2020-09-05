package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.commands.*;
import de.tdrstudios.api.DevTools.DevTools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;


public class sendPNCommand extends Command {

    public sendPNCommand() {

        super(null, "Test PN", CommandType.DISCORD_GUILD, "PN");

    }


    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        Member user = ((DiscordGuildCommandSender) sender).getMember();
        EmbedBuilder eb = new EmbedBuilder();
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String arg : args) {
            if (!first) builder.append(" ");
            builder.append(arg);
            first = false;
        }
        String msg = builder.toString();
        eb.setDescription(msg);

        eb.setTitle("Privat-Nachricht-Test");
        eb.setColor(Color.GREEN);
        if(msg == null) {
            eb.setColor(Color.BLACK);
            eb.setDescription("Test-Nachricht!");
        }
        DevTools.sendPrivateEmbed(user, eb);
    }
}
