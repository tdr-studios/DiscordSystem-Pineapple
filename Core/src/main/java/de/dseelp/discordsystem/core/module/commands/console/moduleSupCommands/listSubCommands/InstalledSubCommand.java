package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.core.spring.components.ModuleService;

public class InstalledSubCommand extends Command {

    public InstalledSubCommand() {
        super(null, "description", CommandType.ALL, "installed");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        StringBuilder builder = new StringBuilder();
        for (Module module : DiscordSystemApplication.getContext().getBean(ModuleService.class).getManager().getModules()) {
            builder.append(module.getName()+":v"+module.getVersion());
            if (module.getDescription() != null) builder.append(" - "+module.getDescription());
            builder.append(System.lineSeparator());
        }
        sender.sendMessage("Installed Modules:");
        sender.sendMessage(builder.toString());
    }
}