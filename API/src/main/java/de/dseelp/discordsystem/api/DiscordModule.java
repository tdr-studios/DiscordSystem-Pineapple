package de.dseelp.discordsystem.api;

<<<<<<< HEAD
import de.dseelp.discordsystem.api.commands.Command;
=======
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
import de.dseelp.modules.Module;

public abstract class DiscordModule extends Module {
    public DiscordModule() {
        super();
    }
<<<<<<< HEAD

    public void registerCommand(Command command) {
       Discord.getBot().getCommandSystem().registerCommand(command);
    }

    public Command getCommand(String name) {
        return Discord.getBot().getCommandSystem().getCommand(name);
    }
=======
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
}
