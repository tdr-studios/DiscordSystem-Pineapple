package de.tdrstudios;

import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.modules.NewModule;

@NewModule(name="say",authors = "TDR_Minecraft",description = "Say somthing as the Bot", version = "1.0")
public class Module extends DiscordModule {
    @Override
    public void onEnable() {
        System.out.println("[TDRTools] -> Module SAY loading");
    }

    @Override
    public void onDisable() {

    }

}
