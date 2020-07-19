package de.dseelp.discordsystem.core.module.commands.guild;

import de.dseelp.discordsystem.api.*;
import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.ObjectUtils;

import java.awt.*;
import java.time.Instant;

public class SetupCommand extends Command {

    public SetupCommand() {
        super(new NullPermission(), "Setup the Bot", CommandType.DISCORD_GUILD, "setup");
    }



    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordGuildCommandSender guildSender = (DiscordGuildCommandSender) sender;
        EmbedBuilder eb = EmbedUtils.createNormalBuilder("Setup" , "Cant resolve the Command! " + System.lineSeparator() + " -> ErrorCode #001 in SetupCommand");
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("AlertChannel")) {
                GuildConfig config = Discord.getGuildManager().getGuildConfig(((DiscordGuildCommandSender) sender).getGuild());
                TextChannel channel = guildSender.getChannel();
                String channelID = channel.getId();
                config.getDocument().add("AlertChannel", channelID);
                Discord.getGuildManager().save(config);
                eb.setDescription("The new Channel for AlertChannel was set to [" + channel.getName() + "]!");
                eb.setColor(Color.GREEN);

            } else if (args[0].equalsIgnoreCase("fix-status")) {
                if(Discord.isMaintenance()) {
                    Discord.getBot().getShardManager().setStatus(OnlineStatus.DO_NOT_DISTURB);
                    Discord.getBot().getShardManager().setActivity(Activity.watching("Maintenance"));
                }else {
                    OnlineStatus status = BotConfig.getOnlineStatus();
                    ActivityType activityType = BotConfig.getActivityType();
                    String activity = BotConfig.getActivityName();

                    if(status.equals(OnlineStatus.DO_NOT_DISTURB)) {
                        System.err.println("[Bot] OnlineStatus DO_Not_DISTRUB is only for Maintenanace");
                        status = OnlineStatus.ONLINE;
                    }
                    Discord.getBot().setStatus(status);
                    Discord.getBot().setActivity(activityType,activity);

                }
                eb.setDescription("The Status and Acticity of the Bot are fixed!");
                eb.setColor(Color.GREEN);


            }

            else {
               SetupHelp(eb);

            }

        }else {
            SetupHelp(eb);

        }

        buildEmbed(eb,sender);
    }

    public void SetupHelp(EmbedBuilder embedBuilder) {

        embedBuilder.setDescription("Setup your Bot! " + System.lineSeparator() + "You can choose between this:" + System.lineSeparator() + System.lineSeparator() + "AlertChannel - Set the Channel for Alerts and More " + System.lineSeparator() + " fix-status - Fix the Activity & OnlineStatus of the Bot");
        embedBuilder.setColor(Color.RED);


    }
    public void buildEmbed(EmbedBuilder eb, CommandSender sender) {
        eb.setAuthor("TDRStudios - Core");
        EmbedUtils.addUserFooter(eb, ((DiscordGuildCommandSender)sender).getAuthor());
        EmbedUtils.setTimestamp(eb, Instant.now());
        sender.sendMessage(eb.build()).queue();
    }


}
