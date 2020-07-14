package de.dseelp.discordsystem.api.events.discord;

import de.dseelp.discordsystem.api.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;

@AllArgsConstructor
public class JDAEvent extends Event {
    @Getter
    private final JDA JDA;
}
