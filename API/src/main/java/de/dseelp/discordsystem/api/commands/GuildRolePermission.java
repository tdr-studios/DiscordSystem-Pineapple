package de.dseelp.discordsystem.api.commands;

public class GuildRolePermission implements Permission {
    @Override
    public boolean check(CommandSender sender) {

        return false;
    }
}
