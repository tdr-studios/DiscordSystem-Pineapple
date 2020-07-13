package de.dseelp.discordsystem.api.commands;

import lombok.Getter;
import net.dv8tion.jda.api.Permission;

public abstract class Command {
    @Getter
    private final String[] names;
    @Getter
    private final Permission permission;
    @Getter
    private final String description;
    @Getter
    private final CommandType type;

    protected Command(String[] names, Permission permission, String description, CommandType type) {
        this.names = names;
        this.permission = permission;
        this.description = description;
        this.type = type;
    }

    public abstract void execute(CommandSender sender, String[] args, Command command);
}
