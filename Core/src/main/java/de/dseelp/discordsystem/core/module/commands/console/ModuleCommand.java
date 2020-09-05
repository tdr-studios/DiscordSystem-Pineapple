package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.InstallSubCommand;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.ListSubCommand;
import de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.UninstallSubCommand;

public class ModuleCommand extends Command {

    public ModuleCommand() {
        super(null, "description", CommandType.CONSOLE, "module", "modules");
        registerSubCommand(new ListSubCommand());
        registerSubCommand(new InstallSubCommand());
        registerSubCommand(new UninstallSubCommand());
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        if (args.length == 0) {
            sendGeneralHelp(sender);
        }
        executeSubCommand(sender, args);
    }
    public void sendGeneralHelp(CommandSender sender) {
        sender.sendMessage("Please use: modules <Action>");
        sender.sendMessage("Actions:");
        sender.sendMessage("list <installed/repos/downloadable> - List all modules");
        sender.sendMessage("install <Module> - Installs a module");
        sender.sendMessage("uninstall <Module> - Uninstalls a module");
        sender.sendMessage("reloadrepos - Reloads all repositories");
    }
}