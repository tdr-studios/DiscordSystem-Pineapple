package de.dseelp.discordsystem.api.commands;

import de.dseelp.discordsystem.api.events.GuildMessageReceivedEvent;
import de.dseelp.discordsystem.utils.EventHandler;
import de.dseelp.discordsystem.utils.Listener;
import lombok.Getter;
import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandSystem {
    private List<Command> commands;
    @Getter
    private CommandListener listener;

    public CommandSystem() {
        commands = new ArrayList<>();
        listener = new CommandListener();
    }

    public void registerCommand(Command command) {
        if (!commandExists(command)) {
            commands.add(command);
        }
    }

    public boolean commandExists(Command command) {
        for (String name : command.getNames()) {
            if (getCommand(name) != null) return true;
        }
        return false;
    }

    public Command getCommand(String name) {
        name = name.toLowerCase();
        for (Command command : commands) {
            for (String commandName : command.getNames()) {
                if (commandName.toLowerCase().equals(name)) {
                    return command;
                }
            }
        }
        return null;
    }

    public void execute(CommandSender sender, ParsedCommand command) {
        if (command == null) return;
        if (CommandType.isSupported(sender, command.getCommand().getType())) {
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
            ParsedCommand command = parseCommand(message);
            DiscordGuildCommandSender commandSender = new DiscordGuildCommandSender(event);
            execute(commandSender, command);
        }
    }
}
