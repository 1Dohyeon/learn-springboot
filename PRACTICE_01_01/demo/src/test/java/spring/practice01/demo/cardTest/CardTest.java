package spring.practice01.demo.cardTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import spring.practice01.demo.card.Card;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.card.CardServiceImpl;
import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

public class CardTest {
    @Test
    void testing() {
        MemoryMember memoryMember = new MemoryMemberImpl();
        CardService cardService = new CardServiceImpl();

        Member member = new Member("abc123", "qwer1234", "Kim");
        memoryMember.save(member);
        Member findMember = memoryMember.findById("abc123");

        Card card = cardService.createCard(member, "KIMCARD", 1000);

        Assertions.assertThat(member).isEqualTo(findMember);
        Assertions.assertThat(card.getPoint()).isEqualTo((int) (1000 + 1000 * 0.01));
        Assertions.assertThat(cardService.findCardNameById("abc123")).isEqualTo("KIMCARD");
    }
}
