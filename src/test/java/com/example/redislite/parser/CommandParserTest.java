package com.example.redislite.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    private final CommandParser parser = new CommandParser();

    @Test
    void shouldParseGetCommand() {

        String[] result = parser.parse("GET name");

        assertArrayEquals(
                new String[]{"GET", "name"},
                result
        );
    }

    @Test
    void shouldParseSetWithSpacesInValue() {

        String[] result =
                parser.parse("SET message Hello World");

        assertArrayEquals(
                new String[]{"SET", "message", "Hello World"},
                result
        );
    }

    @Test
    void shouldParseCommandCaseInsensitively() {

        String[] result = parser.parse("get name");

        assertArrayEquals(
                new String[]{"GET", "name"},
                result
        );
    }

    @Test
    void shouldRejectEmptyCommand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("")
        );
    }
}
