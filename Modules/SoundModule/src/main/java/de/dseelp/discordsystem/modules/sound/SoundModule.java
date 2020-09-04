package de.dseelp.discordsystem.modules.sound;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.modules.NewModule;

@NewModule(name = "Sound", authors = "DSeeLP", version = "0.1-ALPHA")
public class SoundModule extends DiscordModule {

    private AudioPlayerManager playerManager;
    @Override
    public void onEnable() {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
    }
}
