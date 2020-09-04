package de.dseelp.discordsystem.core.module.commands.console.moduleSupCommands;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.commands.CommandType;
import de.dseelp.discordsystem.api.modules.Module;
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
            Module module = bean.getManager().getModule(name);
            if (module == null) {
                sender.sendMessage("No installed module with name " + name + " found!");
                return;
            }
            File file = module.getFile();
            module.setEnabled(false);
            bean.getManager().unload(module);
            System.gc();
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}