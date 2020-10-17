package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.modules.Module;
import de.dseelp.discordsystem.api.modules.ModuleManager;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class UninstallSubCommand extends Command {

    public UninstallSubCommand() {
        super(null, "description", CommandType.ALL, "uninstall");
    }

    @Override
    public void execute(CommandSender sender, String[] args, Command command) {
        if (args.length == 0) {
            System.out.println("more args req");
        }else {
            ModuleService bean = DiscordSystemApplication.getContext().getBean(ModuleService.class);
            String name = args[0];
            ModuleManager manager = bean.getManager();
            Module module = manager.getModule(name);
            if (module == null) {
                sender.sendMessage("No installed module with name " + name + " found!");
                return;
            }
            File file = module.getFile();
            String moduleName = module.getName();
            manager.disable(module);
            manager.unload(module);
            System.gc();
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                System.err.println("Can't delete "+moduleName +" you need to delete it manually when the application is stopped");
                System.err.println("After this the DiscordSystem may not function properly. Please restart the application!");
            }
        }
    }
}