
package com.example.redislite;

import com.example.redislite.store.KeyValueStore;

public class Main {

    public static void main(String[] args) {

        KeyValueStore store = new KeyValueStore();

        store.set("name", "Anna");
        store.set("city", "Tallinn");

        System.out.println("Name: " +
                store.get("name").orElse("Key not found"));

        System.out.println("City: " +
                store.get("city").orElse("Key not found"));

        System.out.println("Age: " +
                store.get("age").orElse("Key not found"));

        store.delete("city");

        System.out.println("City exists: " +
                store.containsKey("city"));

        System.out.println("Store size: " + store.size());
    }
}
