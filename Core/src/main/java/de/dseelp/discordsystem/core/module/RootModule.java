package de.dseelp.discordsystem.core.module;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.core.module.commands.SetStateCommand;
import de.dseelp.discordsystem.core.module.commands.console.StopCommand;
import de.dseelp.discordsystem.core.module.commands.HelpCommand;
import de.dseelp.discordsystem.core.module.commands.guild.SayCommand;
import de.dseelp.discordsystem.core.module.commands.guild.TestCommand;

public class RootModule extends DiscordModule implements Listener {
    @Override
    public void onEnable() {
        HelpCommand helpCommand = new HelpCommand();
        Discord.getEventManager().addListener(this, helpCommand);
        registerCommand(helpCommand);
        registerCommand(new StopCommand());
        registerCommand(new TestCommand());
        registerCommand(new SayCommand());
        registerCommand(new SetStateCommand());
    }


    @Override
    public void onDisable() {

    }
}
