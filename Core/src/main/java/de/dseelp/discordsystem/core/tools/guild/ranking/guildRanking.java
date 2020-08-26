package de.dseelp.discordsystem.core.tools.guild.ranking;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.GuildManager;
import de.dseelp.discordsystem.utils.console.ConsoleColor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.sharding.ShardManager;

public class guildRanking {
    @Getter
    ShardManager bot = Discord.getBot().getShardManager();

    public void createRank(String name, ConsoleColor color, rankType type , boolean isAdmin) {





    }
    //public boolean addPermission (Guild guild, Rank rank) {
    //
    //
    //return true; }




}
