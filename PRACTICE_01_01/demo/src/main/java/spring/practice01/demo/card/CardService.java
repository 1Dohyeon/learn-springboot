package spring.practice01.demo.card;

import spring.practice01.demo.member.Member;

public interface CardService {
    Card createCard(Member member, int cardNumber, String cardName, int point);
}
