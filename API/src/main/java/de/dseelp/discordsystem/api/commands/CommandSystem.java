package de.dseelp.discordsystem.api.commands;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordModule;
import de.dseelp.discordsystem.api.events.discord.guild.GuildMessageReceivedEvent;
import de.dseelp.discordsystem.api.event.EventHandler;
import de.dseelp.discordsystem.api.event.Listener;
import de.dseelp.discordsystem.api.events.system.CommandListRegenerateEvent;
import lombok.Getter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandSystem {
    private HashMap<DiscordModule, List<Command>> commands;
    @Getter
    private CommandListener listener;

    public CommandSystem() {
        commands = new HashMap<>();
        listener = new CommandListener();
    }

    private Command[] commandArray = new Command[]{};

    public Command[] getCommands() {
        return commandArray;
    }

    private void regenerateCommandList() {
        List<Command> commands = new ArrayList<>();
        for (List<Command> value : this.commands.values()) {
            commands.addAll(value);
        }
        commandArray = commands.toArray(new Command[commands.size()]);
        Discord.getEventManager().callEvent(new CommandListRegenerateEvent());
    }

    public void registerCommand(DiscordModule module, Command command) {
        if (!commandExists(command)) {
            commands.computeIfAbsent(module, k -> new ArrayList<>());
            List<Command> commands = this.commands.get(module);
            commands.add(command);
            this.commands.put(module, commands);
            regenerateCommandList();
        }
    }

    public void removeCommandsForModule(DiscordModule module) {
        commands.remove(module);
        regenerateCommandList();
    }

    public boolean commandExists(Command command) {
        for (String name : command.getNames()) {
            if (getCommand(name) != null) return true;
        }
        return false;
    }

    public Command getCommand(String name) {
        name = name.toLowerCase();
        for (Map.Entry<DiscordModule, List<Command>> entry : commands.entrySet()) {
            for (Command command : entry.getValue()) {
                for (String commandName : command.getNames()) {
                    if (commandName.toLowerCase().equals(name)) {
                        return command;
                    }
                }
            }
        }
        return null;
    }

    public void execute(CommandSender sender, ParsedCommand command) {
        if (command == null) return;
        if (CommandType.isSupported(sender, command.getCommand().getTypes())) {
            command.getCommand().execute(sender, command.getArgs(), command.getCommand());
        }
    }

    public ParsedCommand parseCommand(String message) {
        String command = message.split(" ")[0];
        command = command.toLowerCase();
        command = command.replaceAll("[^a-z]", "");
        Pattern pattern = Pattern.compile("\"[^\"]+\"|\\S+");
        // one or more non-whitespace characters or quotes around one or more characters
        Matcher matcher = pattern.matcher(message);
        // check all occurance
        List<String> list = new ArrayList<>();
        boolean first = true;
        while (matcher.find()) {
            String arg = matcher.group();
            if (arg.contains(" ")) arg = arg.replace("\"", "");
            if (!first) list.add(arg);
            first = false;
        }
        String[] args = list.toArray(new String[list.size()]);
        ParsedCommand cmd = new ParsedCommand();
        cmd.setArgs(args);
        cmd.setCommandName(command);
        Command cmdObj = getCommand(command);
        cmd.setCommand(cmdObj);
        if (cmdObj == null) return null;
        return cmd;
    }

    private class CommandListener implements Listener {
        @EventHandler
        public void onGuildMessageReceiveEvent(GuildMessageReceivedEvent event) {
            String message = event.getMessage().getContentRaw();
            if (message.toLowerCase().startsWith(BotConfig.getCommandPrefix())) {
                ParsedCommand command = parseCommand(message);
                DiscordGuildCommandSender commandSender = new DiscordGuildCommandSender(event);
                execute(commandSender, command);
            }
        }
    }
}