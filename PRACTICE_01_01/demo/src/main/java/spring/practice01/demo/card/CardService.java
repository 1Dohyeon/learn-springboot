package spring.practice01.demo.card;

import spring.practice01.demo.member.Member;

public interface CardService {
    void saveCard(Card card, Member member);

    String findCardNameById(String id);

    Card createCard(Member member, String cardName, int point);
}
