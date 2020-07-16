package de.dseelp.discordsystem.api.reload;

import com.google.common.collect.Maps;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReloadManager {
    private HashMap<DiscordModule, List<Reloadable>> reloads = new HashMap<>();

    private Reloadable[] reloadables = new Reloadable[]{};

    public Reloadable[] getReloads() {
        return reloadables;
    }

    public void reload(String name) {
        for (Reloadable reload : getReloads()) {
            if (reload.getReloadName().toLowerCase().equals(name.toLowerCase())) {
                reload(reload);
            }
        }
    }

    public void reload(Reloadable reloadable) {
        Discord.getEventManager().callEvent(new ReloadableReloadEvent(reloadable));
        reloadable.reload();
        Discord.getEventManager().callEvent(new ReloadableReloadedEvent(reloadable));
    }

    private void updateReloads() {
        List<Reloadable> reloads = new ArrayList<>();
        for (Map.Entry<DiscordModule, List<Reloadable>> entry : this.reloads.entrySet()) {
            reloads.addAll(entry.getValue());
        }
        reloadables = reloads.toArray(new Reloadable[reloads.size()]);
    }

    public void addReload(DiscordModule module, Reloadable reloadable) {
        if (!canAdd(reloadable)) return;
        reloads.computeIfAbsent(module, k -> new ArrayList<>());
        List<Reloadable> reloadables = this.reloads.get(module);
        reloadables.add(reloadable);
        this.reloads.put(module, reloadables);
        updateReloads();
    }

    private boolean canAdd(Reloadable reloadable) {
        if (reloadable.getReloadName() == null) return false;
        if (reloadable.getReloadName().equals("")) return false;
        for (Map.Entry<DiscordModule, List<Reloadable>> entry : reloads.entrySet()) {
            for (Reloadable rel : entry.getValue()) {
                if (rel.getReloadName().toLowerCase().equals(reloadable.getReloadName().toLowerCase())) return false;
            }
        }
        return true;
    }

    public void removeReloads(DiscordModule module) {
        reloads.remove(module);
    }
}
