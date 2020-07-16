package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.api.reload.Reloadable;

import java.util.concurrent.TimeUnit;

public class BotReload implements Reloadable {

    @Override
    public void reload() {
        DiscordBot bot = Discord.getBot();
        bot.stop();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bot.start();
    }

    @Override
    public String getReloadName() {
        return "bot";
    }

    @Override
    public String getDescription() {
        return "Restarts the Bot!";
    }
}
