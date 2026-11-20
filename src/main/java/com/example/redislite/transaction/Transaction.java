package com.example.redislite.transaction;

import com.example.redislite.store.KeyValueStore;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Transaction {

    private final Map<String, Optional<String>> changes =
            new LinkedHashMap<>();

    private boolean failed = false;

    public void set(String key, String value) {
        changes.put(key, Optional.of(value));
    }

    public void delete(String key) {
        changes.put(key, Optional.empty());
    }

    public boolean hasChange(String key) {
        return changes.containsKey(key);
    }

    public Optional<String> getValue(String key) {
        return changes.get(key);
    }

    public void markFailed() {
        failed = true;
    }

    public boolean isFailed() {
        return failed;
    }

    public void applyTo(KeyValueStore store) {

        for (Map.Entry<String, Optional<String>> entry
                : changes.entrySet()) {

            String key = entry.getKey();
            Optional<String> value = entry.getValue();

            if (value.isPresent()) {
                store.set(key, value.get());
            } else {
                store.delete(key);
            }
        }
    }
}
