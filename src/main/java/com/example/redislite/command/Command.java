package com.example.redislite.command;

import com.example.redislite.session.ClientSession;

public interface Command {

    String execute(ClientSession session, String[] args);
}
