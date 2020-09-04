package de.dseelp.discordsystem.api.reactions;

import com.vdurmont.emoji.Emoji;
import net.dv8tion.jda.api.entities.Emote;

public interface Reaction {
    String getAsUnicode();
    Emote getAsEmote();
    Emoji getAsEmoji();
}
