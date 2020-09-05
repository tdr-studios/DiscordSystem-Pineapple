package de.dseelp.discordsystem.api.setup;

import de.dseelp.discordsystem.api.commands.CommandSender;

public interface Setup {
    void setup(CommandSender sender, String[] args);
    String getName();
    String getDescription();
}
