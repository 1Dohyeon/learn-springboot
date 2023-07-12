package spring.practice01.demo.member;

public class Member {
    // Id, password, name을 통해서 객체 생성
    private String id;
    private String password;
    private String name;
    private boolean haveCard = false;
    private boolean inMemory = false;
    private String yourCardName = "";

    public String getYourCardName() {
        return yourCardName;
    }

    public void setYourCardName(String yourCardName) {
        this.yourCardName = yourCardName;
    }

    public boolean getInMemory() {
        return inMemory;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public boolean getHaveCard() {
        return haveCard;
    }

    public void setHaveCard(boolean haveCard) {
        this.haveCard = haveCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 생성자
    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
