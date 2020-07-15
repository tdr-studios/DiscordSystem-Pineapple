package de.dseelp.discordsystem.core.module;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.core.module.commands.SetStateCommand;
import de.dseelp.discordsystem.core.module.commands.console.RestartCommand;
import de.dseelp.discordsystem.core.module.commands.console.StopCommand;
import de.dseelp.discordsystem.core.module.commands.HelpCommand;
import de.dseelp.discordsystem.core.module.commands.guild.SayCommand;
import de.dseelp.discordsystem.core.module.commands.guild.SetupCommand;
import de.dseelp.discordsystem.core.module.commands.guild.TestCommand;
import de.tdrstudios.api.DevTools.DevTools;

import java.awt.*;

public class RootModule extends DiscordModule implements Listener {
    @Override
    public void onEnable() {
        HelpCommand helpCommand = new HelpCommand();
        Discord.getEventManager().addListener(this, helpCommand);
        registerCommand(helpCommand);
        registerCommand(new StopCommand());
        registerCommand(new TestCommand());
        registerCommand(new SayCommand());
        if(!Discord.isMaintenance()) {
            registerCommand(new SetStateCommand());
        }

        registerCommand(new RestartCommand());
        registerCommand(new HelpCommand());
        registerCommand(new SetupCommand());
    }


    @Override
    public void onDisable() {

    }

}
