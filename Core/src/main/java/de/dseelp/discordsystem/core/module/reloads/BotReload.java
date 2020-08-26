package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.api.reload.Reloadable;

public class BotReload implements Reloadable {

    @Override
    public void reload() {
        DiscordBot bot = Discord.getBot();
        bot.reload();
    }

    @Override
    public String getReloadName() {
        return "Bot";
    }

    @Override
    public String getDescription() {
        return "Restarts the Bot!";
    }
}
