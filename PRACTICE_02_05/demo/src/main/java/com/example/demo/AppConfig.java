package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.memory.Memory;
import com.example.demo.memory.MemoryImpl;
import com.example.demo.surgery.EyesSurgery;
import com.example.demo.surgery.Surgery;

@Configuration
public class AppConfig {
    @Bean
    public Surgery surgery() {
        return new EyesSurgery(
                memory());
        /*
         * return new NoseSurgery(
         * memory());
         */
    }

    @Bean
    public Memory memory() {
        return new MemoryImpl();
    }
}
