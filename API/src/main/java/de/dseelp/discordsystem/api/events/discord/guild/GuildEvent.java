package de.dseelp.discordsystem.api.events.discord.guild;

import de.dseelp.discordsystem.api.events.discord.JDAEvent;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

public class GuildEvent extends JDAEvent {
    @Getter
    private final Guild guild;

    public GuildEvent(JDA JDA, Guild guild) {
        super(JDA);
        this.guild = guild;
    }
}
