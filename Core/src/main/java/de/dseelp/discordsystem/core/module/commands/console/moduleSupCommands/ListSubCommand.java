package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands.DownloadableSubCommand;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands.InstalledSubCommand;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands.ReposSubCommand;

public class ListSubCommand extends Command {
    public ListSubCommand() {
        super(null, "List Modules", CommandType.CONSOLE, "list");
        registerSubCommand(new InstalledSubCommand());
        registerSubCommand(new ReposSubCommand());
        registerSubCommand(new DownloadableSubCommand());
    }
    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        if (args.length == 0) {
            sender.sendMessage("Please use: module list <installed/repos/downloadable>");
            return;
        }
        executeSubCommand(sender, args);
    }
}
