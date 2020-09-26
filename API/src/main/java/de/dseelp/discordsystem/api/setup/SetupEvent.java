package de.dseelp.discordsystem.api.setup;

import de.dseelp.discordsystem.api.commands.CommandSender;
import de.dseelp.discordsystem.api.event.Event;
import lombok.Getter;

public class SetupEvent extends Event {
    @Getter
    private final Setup setup;
    @Getter
    private final CommandSender sender;
    @Getter
    private final String[] args;

    public SetupEvent(CommandSender sender, String[] args, Setup setup) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

}
