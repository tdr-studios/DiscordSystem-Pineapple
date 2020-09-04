package de.dseelp.discordsystem.api.commands;

public interface Permission {
    boolean check(CommandSender sender);
}
