package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.spring.components.ModuleDownloadService;
import de.dseelp.discordsystem.version.repository.ModuleRepository;

public class ReposSubCommand extends Command {

    public ReposSubCommand() {
        super(null, "description", CommandType.ALL, "repos");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        ModuleDownloadService service = DiscordSystemApplication.getContext().getBean(ModuleDownloadService.class);
        StringBuilder builder = new StringBuilder();
        for (ModuleRepository repository : service.getRepositories()) {
            builder.append(repository.getName());
            //if (repository.getDescription() == null) builder.append(" - "+repository.getDescription());
            builder.append(System.lineSeparator());
        }
        sender.sendMessage("Selected Repositories:");
        sender.sendMessage(builder.toString());
    }
}