package surgery;

import person.Person;

public class NoseSurgery implements Surgery {

    @Override
    public void plasticSurgery(Person person) {
        person.setnose("직선 코");
    }
}
