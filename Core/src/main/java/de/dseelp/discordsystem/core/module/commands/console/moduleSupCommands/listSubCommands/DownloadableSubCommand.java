package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands.listSubCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.spring.components.ModuleDownloadService;
import de.dseelp.discordsystem.version.module.DownloadableModule;

import java.util.Arrays;

public class DownloadableSubCommand extends Command {

    public DownloadableSubCommand() {
        super(null, "description", CommandType.ALL, "downloadable");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        ModuleDownloadService service = DiscordSystemApplication.getContext().getBean(ModuleDownloadService.class);
        StringBuilder builder = new StringBuilder();
        for (DownloadableModule module : service.getModules()) {
            builder.append(module.getName()+":v"+module.getLatestSupported()+ " by "+ Arrays.asList(module.getAuthors()));
            if (module.getDescription() != null) builder.append(" - "+module.getDescription());
            builder.append(System.lineSeparator());
        }
        sender.sendMessage("Downloadable Modules:");
        sender.sendMessage(builder.toString());
    }
}