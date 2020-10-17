package de.dseelp.discordsystem.api.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParsedCommand {
    private String[] args;
    private String commandName;
    private Command command;
    private CommandType type;
}
