package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.reload.ReloadManager;
import de.dseelp.discordsystem.api.reload.Reloadable;

public class AllReload implements Reloadable {

    @Override
    public void reload() {
        ReloadManager reloadManager = Discord.getReloadManager();
        for (Reloadable reload : reloadManager.getReloads()) {
            if (!reload.getClass().getName().equals(getClass().getName())) {
                reload.reload();
            }
        }
    }

    @Override
    public String getReloadName() {
        return "All";
    }

    @Override
    public String getDescription() {
        return "Executes all Reloads";
    }
}
