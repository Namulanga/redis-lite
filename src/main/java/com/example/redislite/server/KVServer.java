package com.example.redislite.server;

import com.example.redislite.command.Command;
import com.example.redislite.command.CommandFactory;
import com.example.redislite.parser.CommandParser;
import com.example.redislite.session.ClientSession;
import com.example.redislite.store.KeyValueStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class KVServer {

    private final int port;

    private final KeyValueStore store;
    private final CommandParser parser;
    private final CommandFactory commandFactory;

    public KVServer(int port) {
        this.port = port;
        this.store = new KeyValueStore();
        this.parser = new CommandParser();
        this.commandFactory = new CommandFactory();
    }

    public void start() {

        System.out.println(
                "Redis Lite server starting on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is ready.");

            while (true) {

                try (Socket clientSocket = serverSocket.accept()) {

                    System.out.println(
                            "Client connected: "
                                    + clientSocket.getInetAddress());

                    handleClient(clientSocket);

                    System.out.println("Client disconnected.");

                } catch (IOException e) {
                    System.err.println(
                            "Client connection error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println(
                    "Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) throws IOException {

        ClientSession session = new ClientSession(store);

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(),
                                StandardCharsets.UTF_8
                        )
                );

                PrintWriter writer = new PrintWriter(
                        socket.getOutputStream(),
                        true,
                        StandardCharsets.UTF_8
                )
        ) {

            writer.println("OK Connected to Redis Lite");

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.equalsIgnoreCase("QUIT")) {
                    writer.println("OK Goodbye");
                    break;
                }

                try {

                    String[] args = parser.parse(line);

                    String commandName = args[0];

                    Command command =
                            commandFactory.getCommand(commandName);

                    String response =
                            command.execute(session, args);

                    writer.println(response);

                } catch (IllegalArgumentException e) {

                    session.markTransactionFailed();

                    writer.println("ERROR " + e.getMessage());
                }
            }
        }
    }
}
