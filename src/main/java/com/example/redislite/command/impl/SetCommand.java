package com.example.redislite.command.impl;

import com.example.redislite.command.Command;
import com.example.redislite.session.ClientSession;

public class SetCommand implements Command {

    @Override
    public String execute(ClientSession session, String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Usage: SET key value");
        }

        String key = args[1];
        String value = args[2];

        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty");
        }

        session.set(key, value);

        return "OK";
    }
}
