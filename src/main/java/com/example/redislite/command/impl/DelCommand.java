package com.example.redislite.command.impl;

import com.example.redislite.command.Command;
import com.example.redislite.session.ClientSession;

public class DelCommand implements Command {

    @Override
    public String execute(ClientSession session, String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Usage: DEL key");
        }

        String key = args[1];

        if (!session.delete(key)) {
            throw new IllegalArgumentException("Key not found");
        }

        return "OK";
    }
}
