package de.dseelp.discordsystem.api.reactions;

import com.vdurmont.emoji.Emoji;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;

public class DiscordGuildEmote implements Reaction {
    public DiscordGuildEmote(String name, Guild guild) {
        List<Emote> emotesByName = guild.getEmotesByName(name, true);
        if (emotesByName.size() != 1) throw new UnsupportedOperationException("Please select the index of the emote!");
        emote = emotesByName.get(0);
    }

    public DiscordGuildEmote(String name, Guild guild, int index) {
        List<Emote> emotesByName = guild.getEmotesByName(name, true);
        emote = emotesByName.get(index);
    }

    private Emote emote;

    @Override
    public String getAsUnicode() {
        return null;
    }

    @Override
    public Emote getAsEmote() {
        return emote;
    }

    @Override
    public Emoji getAsEmoji() {
        return null;
    }


}
