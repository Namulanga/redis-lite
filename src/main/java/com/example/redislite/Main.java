package com.example.redislite;

import com.example.redislite.server.KVServer;

public class Main {

    public static void main(String[] args) {

        int port = 5555;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number");
                return;
            }
        }

        KVServer server = new KVServer(port);

        server.start();
    }
}
