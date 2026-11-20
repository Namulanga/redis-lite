package com.example.redislite.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyValueStoreTest {

    private KeyValueStore store;

    @BeforeEach
    void setUp() {
        store = new KeyValueStore();
    }

    @Test
    void shouldSaveAndGetValue() {
        store.set("name", "Anna");

        assertEquals("Anna", store.get("name").orElse(null));
    }

    @Test
    void shouldReturnEmptyForMissingKey() {
        assertTrue(store.get("unknown").isEmpty());
    }

    @Test
    void shouldUpdateExistingValue() {
        store.set("name", "Anna");
        store.set("name", "Maria");

        assertEquals("Maria", store.get("name").orElse(null));
    }

    @Test
    void shouldDeleteValue() {
        store.set("name", "Anna");

        assertTrue(store.delete("name"));
        assertFalse(store.containsKey("name"));
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingKey() {
        assertFalse(store.delete("unknown"));
    }

    @Test
    void shouldClearStore() {
        store.set("name", "Anna");
        store.set("city", "Tallinn");

        store.clear();

        assertEquals(0, store.size());
    }
}
