package person;

public class Person {
    private String eyes; // 눈
    private String nose; // 코
    private String mouth; // 입

    public String getEyes() {
        return eyes;
    }

    public String getnose() {
        return nose;
    }

    public String getmouth() {
        return mouth;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public void setnose(String nose) {
        this.nose = nose;
    }

    public void setmouth(String mouth) {
        this.mouth = mouth;
    }

    // 생성자
    public Person(String eyes, String nose, String mouth) {
        this.eyes = eyes;
        this.nose = nose;
        this.mouth = mouth;
    }
}
