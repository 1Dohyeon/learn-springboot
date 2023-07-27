package com.example.demo.surgery;

import com.example.demo.memory.Memory;
import com.example.demo.person.Person;

public class NoseSurgery implements Surgery {

    private final Memory memory;

    public NoseSurgery(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void plasticSurgery(Person person) {
        person.setnose("직선 코");
        memory.save("코");
    }

    @Override
    public void find() {
        for (String i : memory.getMemory()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
