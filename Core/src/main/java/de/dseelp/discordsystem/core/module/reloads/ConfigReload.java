package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.reload.Reloadable;

public class ConfigReload implements Reloadable {

    @Override
    public void reload() {
        System.out.println("Reloading Config...");
        BotConfig.load();
        System.out.println("Reloaded Config!");
    }

    @Override
    public String getReloadName() {
        return "Config";
    }

    @Override
    public String getDescription() {
        return "Reloads the Config";
    }
}
