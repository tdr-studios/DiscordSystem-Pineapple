package de.dseelp.discordsystem.api.commands;

public class NullPermission implements Permission{

    @Override
    public boolean check(CommandSender sender) {
        return false;
    }
}
