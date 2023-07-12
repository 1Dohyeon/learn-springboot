package spring.practice01.demo.member;

// 회원 정보
public class Member {
    // Id, password, name을 통해서 객체 생성
    private String id; // 회원 가입에 필요한 id
    private String password; // 회원 가입에 필요한 pw
    private String name; // 회원 가입에 필요한 name
    private boolean haveCard = false; // 카드를 가지고 있는지의 여부
    private boolean inMemory = false; // 메모리에 저장이 되었는지의 여부
    private String yourCardName = ""; // 가지고 있는 카드의 이름

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
