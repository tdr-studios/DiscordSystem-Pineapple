package de.dseelp.discordsystem.api.events;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class GuildMemberEvent extends GuildEvent {
    @Getter
    private final Member member;

    public GuildMemberEvent(JDA JDA, Guild guild, Member member) {
        super(JDA, guild);
        this.member = member;
    }
}
