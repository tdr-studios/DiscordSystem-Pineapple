package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.modules.Module;

public abstract class DiscordModule extends Module {
    public DiscordModule() {
        super();
    }

    public void registerCommand(Command command) {
       Discord.getCommandSystem().registerCommand(this, command);
    }

    public Command getCommand(String name) {
        return Discord.getCommandSystem().getCommand(name);
    }
}
