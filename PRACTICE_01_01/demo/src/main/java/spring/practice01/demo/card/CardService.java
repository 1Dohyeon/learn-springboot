package spring.practice01.demo.card;

import spring.practice01.demo.member.Member;

public interface CardService {
    void saveCard(Card card, Member member); // 카드 정보 저장 메소드

    String findCardNameById(String id); // Member id를 통해 카드 name을 찾는 메소드

    Card createCard(Member member, String cardName, int point); // 카드 객체를 만드는 메소드
}
