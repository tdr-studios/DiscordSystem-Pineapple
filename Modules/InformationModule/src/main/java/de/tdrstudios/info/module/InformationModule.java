package de.tdrstudios.info.module;

import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.commands.Command;
import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.modules.NewModule;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import de.tdrstudios.info.module.commands.Pingcommand;
import de.tdrstudios.info.module.commands.YTcommand;
import lombok.Getter;

import java.util.Timer;
@NewModule(name ="Information Module", authors = "TDR_Minecraft" , description = "TDRStudios Standard Module for Information",version = "1.0" )
public class InformationModule extends DiscordModule {

    @Getter
    private static InformationModule instance;

    @Getter
    private LogSystem logSystem;

    @Override
    public void onEnable() {
        LoggerRegistry.register("informationModuleLogger", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "InfoModule"));
        logSystem = LoggerRegistry.get("informationModuleLogger");
        logSystem.write("init \"infoModule\" Logger!");
        //registerSetups();
        registerCommands();
    }

    public void registerCommands() {
        logSystem.write("Register [YouTube] Command!");
        registerCommand(new YTcommand());
        logSystem.write("Successfully register [YouTube] Command! ");

        logSystem.write("Register [Ping] Command!");
        registerCommand(new Pingcommand());
        logSystem.write("Successfully register [Ping] Command! ");
    }
}
