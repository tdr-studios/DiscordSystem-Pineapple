package de.dseelp.discordsystem.api.commands;

import de.dseelp.discordsystem.api.Discord;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<Command> subCommands = new ArrayList<>();

    public boolean executeSubCommand(CommandSender sender, String[] args) {
        ParsedCommand command = parseCommand(args);
        if (command == null) return false;
        if (CommandType.isSupported(sender, command.getCommand().getTypes())) {
            if (sender.hasPermission(command.getCommand().getPermission())) {
                Discord.getCommandSystem().getExecutorService().execute(() -> command.getCommand().execute(sender, command.getArgs(), command.getCommand()));
                return true;
            }
        }
        return false;
    }

    public void registerSubCommand(Command subCommand) {
        if (!subCommandExists(subCommand)) {
            subCommands.add(subCommand);
        }
    }

    public Command getSubCommand(String name) {
        name = name.toLowerCase();
        for (Command subCommand : subCommands) {
            for (String subCommandName : subCommand.getNames()) {
                if (subCommandName.toLowerCase().equals(name)) return subCommand;
            }
        }
        return null;
    }

    public boolean subCommandExists(Command command) {
        for (String name : command.getNames()) {
            if (getSubCommand(name) != null) return true;
        }
        return false;
    }

    private ParsedCommand parseCommand(String[] args) {
        if (args.length == 0) return null;
        String command = args[0].toLowerCase();
        command = command.replaceAll("[^a-z]", "");
        Command subCmd = getSubCommand(command);
        if (subCmd == null) return null;
        ParsedCommand cmd = new ParsedCommand();
        cmd.setCommandName(command);
        cmd.setCommand(subCmd);
        cmd.setArgs(Arrays.copyOfRange(args, 1, args.length));
        return cmd;
    }

    @Getter
    private boolean commandHidedFromHelp;

    public void setHideCommand(boolean hideCommand) {
        this.commandHidedFromHelp = hideCommand;
    }

    public abstract void execute(CommandSender sender, String[] args, Command command);
}
