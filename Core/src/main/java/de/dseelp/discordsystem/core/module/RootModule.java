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
import de.dseelp.modules.ModuleInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class RootModule extends DiscordModule implements Listener {



    @Override
    public void onEnable() {
        try {
            Constructor<ModuleInfo> constructor = ModuleInfo.class.getDeclaredConstructor(String.class, String[].class, String.class, String.class, String[].class, String[].class);
            constructor.setAccessible(true);
            Object object = constructor.newInstance("DiscordSystem", new String[]{"DSeeLPYT", "TDR_Minecraft"}, "1.0", "This is the Default Module", new String[]{}, new String[]{});
            /*Field field = RootModule.class.getField("info");
            field.setAccessible(true);
            field.set(this, object);*/
            Field info = getClass().getSuperclass().getSuperclass().getDeclaredField("info");
            info.setAccessible(true);
            info.set(this, object);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
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
