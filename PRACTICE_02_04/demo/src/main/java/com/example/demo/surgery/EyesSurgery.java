package com.example.demo.surgery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.memory.Memory;
import com.example.demo.person.Person;

@Component
public class EyesSurgery implements Surgery {

    private final Memory memory;

    @Autowired
    public EyesSurgery(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void plasticSurgery(Person person) {
        person.setEyes("유쌍");
        memory.save("눈");
    }

    @Override
    public void find() {
        for (String i : memory.getMemory()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
