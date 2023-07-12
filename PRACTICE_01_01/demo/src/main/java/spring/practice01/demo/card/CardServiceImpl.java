package spring.practice01.demo.card;

import spring.practice01.demo.member.Member;

public class CardServiceImpl implements CardService {
    @Override
    public Card createCard(Member member, String cardName, int point) {
        if (member.getInMemory() == true) {
            if (member.getHaveCard() == true) {
                System.out.println("이미 카드를 소유 중입니다.");
                return null;
            } else {
                Card card = new Card(cardName, point);
                System.out.println("카드를 만들었습니다, cardname = " + cardName);
                member.setYourCardName(cardName);
                member.setHaveCard(true);
                return card;
            }
        } else {
            System.out.println("회원가입을 먼저 해주세요.");
            return null;
        }
    }

}
