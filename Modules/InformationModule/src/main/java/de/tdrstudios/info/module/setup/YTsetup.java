package de.tdrstudios.info.module.setup;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.EmbedUtils;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.ConsoleCommandSender;
import de.dseelp.discordsystem.api.commands.DiscordGuildCommandSender;
import de.dseelp.discordsystem.api.setup.Setup;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class YTsetup implements Setup {
    @Override
    public void setup(CommandSender commandSender, String[] args) {
        DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;

        if (args.length == 0) {
            sender.sendMessage(EmbedUtils.createError("Setup Error", "Please mention a Link for Youtube")).queue();
        }else {
            if (args.length > 1) {
                sender.sendMessage(EmbedUtils.createError("Setup Error", "Please mention only one Link")).queue();
            }else {

                boolean valid = false;

                if(args[0].startsWith("https://youtube.com/")) {

                    if(args[0].contains("/watch?v=")) {
                        valid = false;
                    } else {
                        valid = true;
                    }

                if(valid) {
                    GuildConfig guildConfig = Discord.getGuildManager().getGuildConfig(sender.getGuild());
                    // guildConfig.getDocument().add("info_yt", args[0]);
                    JsonDocument jsonDocument = guildConfig.getDocument();
                    jsonDocument.add("info_yt", args[0]);
                    guildConfig.setDocument(jsonDocument);
                    Discord.getGuildManager().save(guildConfig);

                    GuildConfig guildConfig2 = Discord.getGuildManager().getGuildConfig(sender.getGuild());

                    String check = guildConfig2.getDocument().getString("info_yt");
                    sender.sendMessage(EmbedUtils.createSuccess("YouTube-link set!", "The YouTube Link was set to "+ check+"!")).queue();
                }else {

                    sender.sendMessage(EmbedUtils.createError("YouTube-Error" , " The Link isn't a YouTube Channel Link")).queue();

                }




            }
        }
    }


}

    @Override
    public String getName() {
        return "info_yt";
    }

    @Override
    public String getDescription() {
        return "Set a Link for the " + BotConfig.getCommandPrefix() + "yt Command!";
    }
}
