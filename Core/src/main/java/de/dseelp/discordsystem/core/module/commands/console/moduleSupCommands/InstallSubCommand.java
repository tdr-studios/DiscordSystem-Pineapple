package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.core.spring.components.ModuleDownloadService;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.dseelp.discordsystem.version.module.DownloadableModule;

import java.util.Arrays;

public class InstallSubCommand extends Command {

    public InstallSubCommand() {
        super(null, "description", CommandType.ALL, "install");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        ModuleDownloadService service = DiscordSystemApplication.getContext().getBean(ModuleDownloadService.class);
        if (args.length == 0) {
            sender.sendMessage("Please use: module install [Authors] <Module>");
            //sendGeneralHelp(sender);
            return;
        }
        String name = args[0];
        if (args.length > 1) {
            DownloadableModule module = service.findModule(Arrays.copyOfRange(args, 2, args.length), service.findModules(name));
            if (module != null) {
                service.downloadModule(module, (ModuleService.CustomModuleManager) DiscordSystemApplication.getContext().getBean(ModuleService.class).getManager());
            }
        }else {
            DownloadableModule[] modules = service.findModules(name);
            if (modules.length == 1) {
                DownloadableModule module = modules[0];
                if (module != null) {
                    service.downloadModule(module, (ModuleService.CustomModuleManager) DiscordSystemApplication.getContext().getBean(ModuleService.class).getManager());
                }
            }else {
                StringBuilder builder = new StringBuilder();
                for (DownloadableModule module : modules) {
                    builder.append(module.getName()+":v"+module.getLatestSupported()+" from "+Arrays.toString(module.getAuthors()));
                    if (module.getDescription() == null) builder.append(" - "+module.getDescription());
                    builder.append(System.lineSeparator());
                }
                System.out.println(builder);
            }
        }
    }
}