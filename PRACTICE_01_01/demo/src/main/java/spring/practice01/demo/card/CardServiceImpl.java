package spring.practice01.demo.card;

import spring.practice01.demo.member.Member;

public class CardServiceImpl implements CardService {
    @Override
    public Card createCard(Member member, int cardNumber, String cardName, int point) {
        if (member.getHaveCard() == true) {
            System.out.println("이미 카드를 소유 중입니다.");
            return null;
        } else {
            Card card = new Card(cardNumber, cardName, point);
            member.setHaveCard(true);
            return card;
        }
    }
}
