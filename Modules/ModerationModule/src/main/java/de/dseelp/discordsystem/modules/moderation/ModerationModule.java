package de.dseelp.discordsystem.modules.moderation;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.modules.NewModule;
import de.dseelp.discordsystem.api.setup.SetupManager;
import de.dseelp.discordsystem.modules.moderation.commands.SuggestionCommand;
import de.dseelp.discordsystem.modules.moderation.setups.SuggestionSetup;

@NewModule(name = "Moderation", authors = "DSeeLP", version = "0.2-ALPHA")
public class ModerationModule extends DiscordModule {
    @Override
    public void onEnable() {
        registerSetups();
        registerCommands();
    }
    public void registerSetups() {
        SetupManager setupManager = Discord.getSetupManager();
        setupManager.addReload(this, new SuggestionSetup());
    }




    public void registerCommands() {
        registerCommand(new SuggestionCommand());
    }
}
