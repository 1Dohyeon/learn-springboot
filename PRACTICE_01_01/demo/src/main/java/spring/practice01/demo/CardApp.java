package spring.practice01.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import spring.practice01.demo.card.Card;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;

public class CardApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemoryMember memoryMember = applicationContext.getBean("memoryMember", MemoryMember.class);
        CardService cardService = applicationContext.getBean("cardService", CardService.class);
        ((AbstractApplicationContext) applicationContext).close();

        Member member1 = new Member("abc123", "qwer1234", "Kim");
        Member member2 = new Member("xyz123", "qwer1234", "Park");
        Member member3 = new Member("kim123", "abcd1234", "Kim");
        System.out.println("---------------------------------");

        memoryMember.save(member1);
        memoryMember.save(member2);
        memoryMember.save(member3);
        System.out.println("---------------------------------");

        memoryMember.findById("abc123");
        memoryMember.findById("xyz123");
        memoryMember.findById("kim123");
        System.out.println("---------------------------------");

        Card card1 = cardService.createCard(member1, "KIMCARD", 0);
        System.out.println("---------------------------------");
        Card card2 = cardService.createCard(member2, "PARKCARD", 5000);
        System.out.println("---------------------------------");
        Card card3 = cardService.createCard(member2, "PARKCARD", 5000); // 똑같은 사람이 카드를 더 만듦
        System.out.println("---------------------------------");
        Card card4 = cardService.createCard(member3, "KIMCARD", 5000); // 같은 이름의 카드가 존재함
        System.out.println("---------------------------------");

        System.out.println(card1.getCardName() + " = " + card1.getPoint());
        System.out.println(card2.getCardName() + " = " + card2.getPoint());
        System.out.println("---------------------------------");

        System.out.println("abc123님의 카드 이름은 " + cardService.findCardNameById("abc123"));
        System.out.println("---------------------------------");
        System.out.println("xyz123님의 카드 이름은 " + cardService.findCardNameById("xyz123"));
        System.out.println("---------------------------------");
        System.out.println("qwer1234님의 카드 이름은 " + cardService.findCardNameById("kim123"));
    }
}
