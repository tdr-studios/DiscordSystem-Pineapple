package de.dseelp.discordsystem.core.module;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.event.EventHandler;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.api.events.system.modules.*;
import de.dseelp.discordsystem.api.reload.ReloadableReloadEvent;
import de.dseelp.discordsystem.api.reload.ReloadableReloadedEvent;
import de.dseelp.discordsystem.core.module.commands.SetActivityCommand;
import de.dseelp.discordsystem.core.module.commands.SetStateCommand;
import de.dseelp.discordsystem.core.module.commands.console.ModuleCommand;
import de.dseelp.discordsystem.core.module.commands.console.ReloadCommand;
import de.dseelp.discordsystem.core.module.commands.console.RestartCommand;
import de.dseelp.discordsystem.core.module.commands.console.StopCommand;
import de.dseelp.discordsystem.core.module.commands.HelpCommand;
import de.dseelp.discordsystem.core.module.commands.guild.SayCommand;
import de.dseelp.discordsystem.core.module.commands.guild.SetupCommand;
import de.dseelp.discordsystem.core.module.commands.guild.TestCommand;
import de.dseelp.discordsystem.api.modules.ModuleInfo;
import de.dseelp.discordsystem.core.module.reloads.AllReload;
import de.dseelp.discordsystem.core.module.reloads.BotReload;
import de.dseelp.discordsystem.core.module.reloads.ConfigReload;
import de.dseelp.discordsystem.core.module.reloads.ModuleReload;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;

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
        Discord.getEventManager().addListener(this, this);
        registerCommand(helpCommand);
        registerCommand(new StopCommand());
        registerCommand(new TestCommand());
        registerCommand(new SayCommand());
        registerCommand(new ReloadCommand());
        registerCommand(new ModuleCommand());
        Discord.getReloadManager().addReload(this, new AllReload());
        Discord.getReloadManager().addReload(this, new BotReload());
        Discord.getReloadManager().addReload(this, new ConfigReload());
        Discord.getReloadManager().addReload(this, new ModuleReload());
        if(!Discord.isMaintenance()) {
            registerCommand(new SetStateCommand());

        }
        registerCommand(new SetActivityCommand());

        //registerCommand(new RestartCommand());
        registerCommand(new SetupCommand());
        logSystem = LoggerRegistry.get("modules");
        LoggerRegistry.register("reloads", ConsoleSystem.createSubLogger(LoggerRegistry.get().getLogger(), "ReloadManager"));
    }

    private LogSystem logSystem;


    @EventHandler
    public void onModuleLoad(ModuleLoadEvent event) {
        logSystem.write("Loading Module "+event.getClassLoader().getInfo().getName()+"!");
    }

    @EventHandler
    public void onModuleLoadFailure(ModuleLoadFailureEvent event) {
        logSystem.error("Failed to load Module "+event.getFile().getName()+"! "+event.getMessage());
    }

    @EventHandler
    public void onModuleLoadFinishedEvent(ModuleLoadFinishedEvent event) {
        logSystem.write("Loaded module "+ event.getClassLoader().getInfo().getName()+"!");
    }

    @EventHandler
    public void onModuleEnabling(ModuleEnableEvent event) {
        logSystem.write("Enabling Module " +event.getModule().getName()+ " v"+event.getModule().getVersion());
    }
    @EventHandler
    public void onModuleEnabled(ModuleEnableFinishedEvent event) {
        logSystem.write("Enabled Module " +event.getModule().getName()+ " v"+event.getModule().getVersion());
    }

    @EventHandler
    public void onReload(ReloadableReloadEvent event) {
        LoggerRegistry.get("reloads").write("Reloading...");
    }

    @EventHandler
    public void onReloaded(ReloadableReloadedEvent event) {
        LoggerRegistry.get("reloads").write("Reload Complete!");
    }

}
