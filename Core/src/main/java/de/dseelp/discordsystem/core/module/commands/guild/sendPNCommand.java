package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.tdrstudios.api.DevTools.DevTools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;


public class sendPNCommand extends Command {

    public sendPNCommand() {

        super(null, "Test PN", CommandType.DISCORD_GUILD, "PN");

    }


    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        Member user = (Member) sender;
        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription("Test-Text!");
        DevTools.sendPrivateEmbed(user, eb);
    }
}
