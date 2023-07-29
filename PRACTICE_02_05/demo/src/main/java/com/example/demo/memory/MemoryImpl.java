package com.example.demo.memory;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MemoryImpl implements Memory {
    private static ArrayList<String> store = new ArrayList<String>();

    @Override
    public void save(String kind) {
        store.add(kind);
    }

    @Override
    public ArrayList<String> getMemory() {
        return store;
    }
}
