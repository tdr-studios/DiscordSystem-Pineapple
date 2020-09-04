package de.dseelp.discordsystem.api.commands;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class RolePermission implements Permission {
    @Getter
    private final String roleName;

    public RolePermission(String roleName) {
        this.roleName = roleName.toLowerCase();
    }

    @Override
    public boolean check(CommandSender commandSender) {
        if (commandSender instanceof DiscordGuildCommandSender) {
            DiscordGuildCommandSender sender = (DiscordGuildCommandSender) commandSender;
            List<Role> roleList = sender.getGuild().getRolesByName(roleName, true);
            return sender.getMember().getRoles().stream().anyMatch(role -> roleList.stream().anyMatch(role1 -> role.getIdLong()==role1.getIdLong()));
        }else return true;
    }
}
