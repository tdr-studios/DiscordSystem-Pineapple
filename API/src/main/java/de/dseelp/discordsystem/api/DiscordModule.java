package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.modules.Module;

public abstract class DiscordModule extends Module {
    public DiscordModule() {
        super();
    }

    public void registerCommand(Command command) {
       Discord.getBot().getCommandSystem().registerCommand(command);
    }

    public Command getCommand(String name) {
        return Discord.getBot().getCommandSystem().getCommand(name);
    }
}
