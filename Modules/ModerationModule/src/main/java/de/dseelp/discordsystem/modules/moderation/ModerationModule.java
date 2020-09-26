package de.dseelp.discordsystem.modules.moderation;

import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.modules.NewModule;
import de.dseelp.discordsystem.api.setup.SetupManager;
import de.dseelp.discordsystem.modules.moderation.commands.BugReportCommand;
import de.dseelp.discordsystem.modules.moderation.commands.ClearCommand;
import de.dseelp.discordsystem.modules.moderation.commands.SuggestionCommand;
import de.dseelp.discordsystem.modules.moderation.setups.BugReportSetup;
import de.dseelp.discordsystem.modules.moderation.setups.SuggestionSetup;

@NewModule(name = "Moderation", authors = "DSeeLP", version = "1.0")
public class ModerationModule extends DiscordModule {
    @Override
    public void onEnable() {
        registerSetups();
        registerCommands();
    }
    public void registerSetups() {
        SetupManager setupManager = Discord.getSetupManager();
        setupManager.addSetup(this, new SuggestionSetup());
        setupManager.addSetup(this, new BugReportSetup());
    }
    public void registerCommands() {
        registerCommand(new SuggestionCommand());
        registerCommand(new BugReportCommand());
        registerCommand(new ClearCommand());
    }
}
