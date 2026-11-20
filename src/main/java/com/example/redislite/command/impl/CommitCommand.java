package com.example.redislite.command.impl;

import com.example.redislite.command.Command;
import com.example.redislite.session.ClientSession;

public class CommitCommand implements Command {

    @Override
    public String execute(ClientSession session, String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: COMMIT");
        }

        return session.commit();
    }
}
