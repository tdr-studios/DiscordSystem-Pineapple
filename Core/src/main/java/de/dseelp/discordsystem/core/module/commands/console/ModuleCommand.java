package de.dseelp.discordsystem.core.module.commands.console;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.core.spring.components.ModuleDownloadService;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.dseelp.discordsystem.version.module.DownloadableModule;
import de.dseelp.discordsystem.version.repository.ModuleRepository;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ModuleCommand extends Command {

    public ModuleCommand() {
        super("A Command To manage modules", CommandType.CONSOLE, "module", "modules");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        if (args.length == 0) {
            sendGeneralHelp(sender);
        }else {
            ModuleDownloadService service = DiscordSystemApplication.getContext().getBean(ModuleDownloadService.class);
            String action = args[0].toLowerCase();
            if (action.equals("list")) {
                if (args.length > 1) {
                    String listAction = args[1].toLowerCase();
                    if (listAction.equals("installed")) {
                        StringBuilder builder = new StringBuilder();
                        for (Module module : DiscordSystemApplication.getContext().getBean(ModuleService.class).getManager().getModules()) {
                            builder.append(module.getName()+":v"+module.getVersion());
                            if (module.getDescription() != null) builder.append(" - "+module.getDescription());
                            builder.append(System.lineSeparator());
                        }
                        sender.sendMessage("Installed Modules:");
                        sender.sendMessage(builder.toString());
                    }else if (listAction.equals("repos")) {
                        StringBuilder builder = new StringBuilder();
                        for (ModuleRepository repository : service.getRepositories()) {
                            builder.append(repository.getName());
                            //if (repository.getDescription() == null) builder.append(" - "+repository.getDescription());
                            builder.append(System.lineSeparator());
                        }
                        sender.sendMessage("Selected Repositories:");
                        sender.sendMessage(builder.toString());
                    }else if (listAction.equals("downloadable")) {
                        StringBuilder builder = new StringBuilder();
                        for (DownloadableModule module : service.getModules()) {
                            builder.append(module.getName()+":v"+module.getLatestSupported()+ " by "+ Arrays.asList(module.getAuthors()));
                            if (module.getDescription() != null) builder.append(" - "+module.getDescription());
                            builder.append(System.lineSeparator());
                        }
                        sender.sendMessage("Downloadable Modules:");
                        sender.sendMessage(builder.toString());
                    }
                }else {
                    sendGeneralHelp(sender);
                }
            }else if (action.equals("install")) {
                if (args.length == 1) {
                    sendGeneralHelp(sender);
                    return;
                }
                String name = args[1];
                if (args.length > 2) {
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
            }else if (action.equals("uninstall")) {
                ModuleService bean = DiscordSystemApplication.getContext().getBean(ModuleService.class);
                if (args.length > 1) {
                    String name = args[1];
                    Module module = bean.getManager().getModule(name);
                    if (module == null) {
                        sender.sendMessage("No installed module with name "+ name+ " found!");
                        return;
                    }
                    File file = module.getFile();
                    bean.getManager().unload(module);
                    System.gc();
                    try {
                        FileUtils.forceDelete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    sendGeneralHelp(sender);
                }
            }else if (action.equals("reload")) {
                service.loadRepositories();
            }
        }
    }

    public void sendGeneralHelp(CommandSender sender) {
        sender.sendMessage("Please use: modules <Action>");
        sender.sendMessage("Actions:");
        sender.sendMessage("list <installed/repos/downloadable> - List all modules");
        sender.sendMessage("install <Module> - Installs a module");
        sender.sendMessage("uninstall <Module> - Uninstalls a module");
        sender.sendMessage("reload - Reloads all repositories");
    }
}
