package spring.practice01.demo.card;

public class Card {
    private String cardName;
    private int point;
    private boolean inCardMemory = false;

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
