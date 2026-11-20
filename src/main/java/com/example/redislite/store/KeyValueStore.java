package com.example.redislite.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KeyValueStore {

    private final Map<String, String> data = new HashMap<>();

    public void set(String key, String value) {
        data.put(key, value);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(data.get(key));
    }

    public boolean delete(String key) {
        return data.remove(key) != null;
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public int size() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }
}
