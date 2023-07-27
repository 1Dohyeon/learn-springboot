package surgery;

import memory.Memory;
import person.Person;

public class EyesSurgery implements Surgery {

    private final Memory memory;

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
