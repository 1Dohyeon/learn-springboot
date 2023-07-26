package surgery;

import person.Person;

public class EyesSurgery implements Surgery {

    @Override
    public void plasticSurgery(Person person) {
        person.setEyes("유쌍");
    }
}
