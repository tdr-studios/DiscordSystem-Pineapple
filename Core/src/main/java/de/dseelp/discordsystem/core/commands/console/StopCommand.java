package de.dseelp.discordsystem.core.commands.console;

import de.dseelp.discordsystem.core.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;

public class StopCommand extends Command {

    public StopCommand() {
        super(new String[]{"stop", "exit", "shutdown"}, null, "Stops the Application", CommandType.CONSOLE);
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordSystemApplication.shutdown();
    }
}
