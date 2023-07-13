package spring.practice01.demo;

import spring.practice01.demo.card.Card;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;

public class CardApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemoryMember memoryMember = appConfig.memoryMember();
        CardService cardService = appConfig.cardService();

        Member member1 = new Member("abc123", "qwer1234", "Kim");
        Member member2 = new Member("xyz123", "qwer1234", "Park");
        Member member3 = new Member("xyz123", "abcd1234", "Lee");
        System.out.println("---------------------------------");

        memoryMember.save(member1);
        memoryMember.save(member2);
        memoryMember.save(member3);
        System.out.println("---------------------------------");

        memoryMember.findById("abc123");
        memoryMember.findById("xyz123");
        memoryMember.findById("qwer1234");
        System.out.println("---------------------------------");

        Card card1 = cardService.createCard(member1, "KIMCARD", 0);
        Card card2 = cardService.createCard(member2, "PARKCARD", 5000);
        Card card3 = cardService.createCard(member3, "LEECARD", 10000);
        System.out.println("---------------------------------");

        System.out.println(card1.getCardName() + " = " + card1.getPoint());
        System.out.println(card2.getCardName() + " = " + card2.getPoint());
        System.out.println(card3.getCardName() + " = " + card3.getPoint());

    }
}
