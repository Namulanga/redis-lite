package com.example.redislite.parser;

import java.util.Locale;

public class CommandParser {

    public String[] parse(String line) {

        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Empty command");
        }

        String input = line.stripLeading();

        int commandEnd = 0;

        while (commandEnd < input.length()
                && !Character.isWhitespace(input.charAt(commandEnd))) {
            commandEnd++;
        }

        String command = input.substring(0, commandEnd)
                .toUpperCase(Locale.ROOT);

        if (commandEnd == input.length()) {
            return new String[]{command};
        }

        String rest = input.substring(commandEnd + 1)
                .stripLeading();

        if (command.equals("SET")) {

            int keyEnd = 0;

            while (keyEnd < rest.length()
                    && !Character.isWhitespace(rest.charAt(keyEnd))) {
                keyEnd++;
            }

            if (keyEnd == rest.length()) {
                return new String[]{command, rest};
            }

            String key = rest.substring(0, keyEnd);
            String value = rest.substring(keyEnd + 1);

            return new String[]{command, key, value};
        }

        if (rest.isBlank()) {
            return new String[]{command};
        }

        String[] arguments = rest.trim().split("\\s+");

        String[] result = new String[arguments.length + 1];
        result[0] = command;

        System.arraycopy(
                arguments, 0,
                result, 1,
                arguments.length
        );

        return result;
    }
}
