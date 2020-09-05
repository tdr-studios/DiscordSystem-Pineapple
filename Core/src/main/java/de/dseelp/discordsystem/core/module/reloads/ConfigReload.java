package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.reload.Reloadable;
import lombok.Getter;

public class ConfigReload implements Reloadable {

   public static String rlName;


    @Override
    public void reload() {
        System.out.println("Reloading Config...");
        BotConfig.load();
        System.out.println("Reloaded Config!");
    }

    @Override
    public String getReloadName() {
        return rlName;
    }

    public static void setReloadName(String newName) {
        rlName = newName;
        System.out.println("[ReloadSystem] The ReloadName was set to " + rlName);
    }

    @Override
    public String getDescription() {
        return "Reloads the Config";
    }
}
