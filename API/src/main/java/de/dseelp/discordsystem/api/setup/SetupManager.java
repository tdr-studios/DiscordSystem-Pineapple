package de.dseelp.discordsystem.api.setup;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.commands.CommandSender;

import java.util.*;

public class SetupManager {
    private HashMap<DiscordModule, List<Setup>> reloads = new HashMap<>();

    private Setup[] setups = new Setup[]{};

    public Setup[] getSetups() {
        return setups;
    }

    public boolean setup(CommandSender sender, String[] args) {
        for (Setup setup : getSetups()) {
            if (setup.getName().toLowerCase().equals(args[0].toLowerCase())) {
                setup(sender, Arrays.copyOfRange(args, 1, args.length), setup);
                return true;
            }
        }
        return false;
    }

    public void setup(CommandSender sender, String[] args, Setup setup) {
        Discord.getEventManager().callEvent(new SetupEvent(sender, args, setup));
        setup.setup(sender, args);
    }

    private void updateSetups() {
        List<Setup> reloads = new ArrayList<>();
        for (Map.Entry<DiscordModule, List<Setup>> entry : this.reloads.entrySet()) {
            reloads.addAll(entry.getValue());
        }
        setups = reloads.toArray(new Setup[reloads.size()]);
    }

    public void addSetup(DiscordModule module, Setup setup) {
        if (!canAdd(setup)) return;
        reloads.computeIfAbsent(module, k -> new ArrayList<>());
        List<Setup> setups = this.reloads.get(module);
        setups.add(setup);
        this.reloads.put(module, setups);
        updateSetups();
    }

    private boolean canAdd(Setup setup) {
        if (setup.getName() == null) return false;
        if (setup.getName().equals("")) return false;
        for (Map.Entry<DiscordModule, List<Setup>> entry : reloads.entrySet()) {
            for (Setup rel : entry.getValue()) {
                if (rel.getName().toLowerCase().equals(setup.getName().toLowerCase())) return false;
            }
        }
        return true;
    }

    public void removeSetups(DiscordModule module) {
        reloads.remove(module);
    }
}
