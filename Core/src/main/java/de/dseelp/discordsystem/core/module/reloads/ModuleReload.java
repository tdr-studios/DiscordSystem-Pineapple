package de.dseelp.discordsystem.core.module.reloads;

import de.dseelp.discordsystem.DiscordSystemApplication;
import de.dseelp.discordsystem.api.reload.Reloadable;
import de.dseelp.discordsystem.core.spring.components.ModuleService;

public class ModuleReload implements Reloadable {

    @Override
    public void reload() {
        ModuleService service = DiscordSystemApplication.getContext().getBean(ModuleService.class);
        service.stop();
        service.load();
        service.enableAll();
    }

    @Override
    public String getReloadName() {
        return "Module";
    }

    @Override
    public String getDescription() {
        return "Reloads the modules!";
    }
}
