package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;

public class StopCommand extends Command {

    public StopCommand() {
        super(null, "Stops the Application", CommandType.CONSOLE, "stop", "exit", "shutdown");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        DiscordSystemApplication.stopServices();
        System.exit(0);
    }
}
