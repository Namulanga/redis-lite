package com.example.redislite.session;

import com.example.redislite.store.KeyValueStore;
import com.example.redislite.transaction.Transaction;

import java.util.Optional;

public class ClientSession {

    private final KeyValueStore store;

    private Transaction transaction;

    public ClientSession(KeyValueStore store) {
        this.store = store;
    }

    public void set(String key, String value) {

        if (transaction != null) {
            transaction.set(key, value);
        } else {
            store.set(key, value);
        }
    }

    public Optional<String> get(String key) {

        if (transaction != null
                && transaction.hasChange(key)) {

            return transaction.getValue(key);
        }

        return store.get(key);
    }

    public boolean delete(String key) {

        if (get(key).isEmpty()) {
            return false;
        }

        if (transaction != null) {
            transaction.delete(key);
        } else {
            store.delete(key);
        }

        return true;
    }

    public void begin() {

        if (transaction != null) {
            throw new IllegalArgumentException(
                    "Transaction already started");
        }

        transaction = new Transaction();
    }

    public String commit() {

        if (transaction == null) {
            throw new IllegalArgumentException(
                    "No active transaction");
        }

        if (transaction.isFailed()) {
            transaction = null;
            return "ERROR Transaction aborted";
        }

        transaction.applyTo(store);
        transaction = null;

        return "OK";
    }

    public void markTransactionFailed() {

        if (transaction != null) {
            transaction.markFailed();
        }
    }

    public boolean isInTransaction() {
        return transaction != null;
    }
}
