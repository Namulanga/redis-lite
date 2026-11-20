package com.example.redislite.command.impl;

import com.example.redislite.command.Command;
import com.example.redislite.session.ClientSession;

public class GetCommand implements Command {

    @Override
    public String execute(ClientSession session, String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Usage: GET key");
        }

        String key = args[1];

        return session.get(key)
                .orElseThrow(() ->
                        new IllegalArgumentException("Key not found"));
    }
}
