package de.dseelp.discordsystem.api.reactions;

import net.dv8tion.jda.api.entities.Emote;

public interface Reaction {
    String getAsUnicode();
    Emote getAsEmote();
}
