package de.dseelp.discordsystem.api;

import de.dseelp.discordsystem.api.events.GuildMessageReceivedEvent;
import de.dseelp.discordsystem.utils.EventHandler;
import de.dseelp.discordsystem.utils.EventManager;
import de.dseelp.discordsystem.utils.Listener;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.internal.entities.EmoteImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] argeeees) {
        String message = "/project create \"Test Project\" \"This is a description\"";
        String command = message.split(" ")[0];
        command = command.toLowerCase();
        System.out.println(command.startsWith("/"));
        command = command.replaceAll("[^a-z]", "");
        System.out.println("Command: "+command);
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
        System.out.println("Args:");
        String[] args = list.toArray(new String[list.size()]);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
