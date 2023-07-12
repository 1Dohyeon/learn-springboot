package spring.practice01.demo;

import spring.practice01.demo.card.Card;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.card.CardServiceImpl;
import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

public class CardApp2 {
    public static void main(String[] args) {
        MemoryMember memoryMember = new MemoryMemberImpl();
        CardService cardService = new CardServiceImpl();

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
        System.out.println("---------------------------------");

        System.out.println(card1.getCardName() + " = " + card1.getPoint());
        System.out.println(card2.getCardName() + " = " + card2.getPoint());
        System.out.println("---------------------------------");

        System.out.println("abc123님의 카드 이름은 " + cardService.findCardNameById("abc123"));
        System.out.println("---------------------------------");
        System.out.println("xyz123님의 카드 이름은 " + cardService.findCardNameById("xyz123"));
        System.out.println("---------------------------------");
        System.out.println("qwer1234님의 카드 이름은 " + cardService.findCardNameById("qwer1234"));
    }
}
