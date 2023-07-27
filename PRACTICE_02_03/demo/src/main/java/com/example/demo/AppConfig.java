package com.example.demo;

import com.example.demo.memory.Memory;
import com.example.demo.memory.MemoryImpl;
import com.example.demo.surgery.EyesSurgery;
import com.example.demo.surgery.Surgery;

public class AppConfig {
    public Surgery surgery() {
        return new EyesSurgery(
                memory());
        /*
         * return new NoseSurgery(
         * memory());
         */
    }

    public Memory memory() {
        return new MemoryImpl();
    }
}
