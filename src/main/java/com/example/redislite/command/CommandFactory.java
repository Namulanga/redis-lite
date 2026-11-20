package com.example.redislite.command;

import com.example.redislite.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory() {
        commands.put("GET", new GetCommand());
        commands.put("SET", new SetCommand());
        commands.put("DEL", new DelCommand());
        commands.put("BEGIN", new BeginCommand());
        commands.put("COMMIT", new CommitCommand());
    }

    public Command getCommand(String name) {

        Command command = commands.get(name.toUpperCase());

        if (command == null) {
            throw new IllegalArgumentException(
                    "Unknown command: " + name);
        }

        return command;
    }
}
