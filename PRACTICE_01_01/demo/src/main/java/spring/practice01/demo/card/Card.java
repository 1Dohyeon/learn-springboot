package spring.practice01.demo.card;

// 카드 정보
public class Card {
    private String cardName; // 카드 이름
    private int point; // 카드 포인트
    private boolean inCardMemory = false; // 카드가 메모리에 들어있는지의 여부

    public boolean getInCardMemory() {
        return inCardMemory;
    }

    public void setInCardMemory(boolean inCardMemory) {
        this.inCardMemory = inCardMemory;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    // 생성자
    public Card(String cardName, int point) {
        this.cardName = cardName;
        this.point = (int) (point + point * 0.01);
    }

    public void chargeCard(int point) {
        if (point >= 100) {
            this.point = (int) (point + point * 0.01);
        } else {
            System.out.println("100원 미만은 충전할 수 없습니다.");
        }
    }
}
