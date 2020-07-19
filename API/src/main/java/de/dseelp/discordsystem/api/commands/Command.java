package de.dseelp.discordsystem.api.commands;

import lombok.Getter;

public abstract class Command {
    @Getter
    private final String[] names;
    @Getter
    private final Permission permission;
    @Getter
    private final String description;
    @Getter
    private final CommandType[] types;

    public Command(Permission permission, String description, CommandType type, String... names) {
        this.names = names;
        this.permission = permission;
        this.description = description;
        this.types = new CommandType[]{type};
    }

    public Command(Permission permission, String description, CommandType[] types, String... names) {
        this.names = names;
        this.permission = permission;
        this.description = description;
        this.types = types;
    }

    public Command(String name, String description, CommandType... types) {
        this.names = new String[]{name};
        this.types = types;
        this.description = description;
        this.permission = null;
    }

    public Command(String name, Permission permission, CommandType... types) {
        this.names = new String[]{name};
        this.permission = permission;
        this.types = types;
        this.description = "";
    }

    public Command(String description, CommandType type, String... names) {
        this.names = names;
        this.description = description;
        this.types = new CommandType[]{type};
        this.permission = null;
    }

    public Command(String description, CommandType[] types, String... names) {
        this.names = names;
        this.description = description;
        this.types = types;
        this.permission = null;
    }

    public abstract void execute(CommandSender sender, String[] args, Command command);
}
