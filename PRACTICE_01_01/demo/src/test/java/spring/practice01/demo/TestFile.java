package spring.practice01.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import spring.practice01.demo.AppConfig;
import spring.practice01.demo.card.Card;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

public class TestFile {

    @Test
    void memberTest() {
        MemoryMember memoryMember = new MemoryMemberImpl();

        Member member = new Member("abc123", "qwer1234", "Kim");
        memoryMember.save(member);
        Member findMember = memoryMember.findById("abc123");

        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void cardTest() {
        AppConfig appConfig = new AppConfig();
        MemoryMember memoryMember = appConfig.memoryMember();
        CardService cardService = appConfig.cardService();

        Member member = new Member("xyz123", "qwer1234", "Park");
        memoryMember.save(member);

        Card card = cardService.createCard(member, "PARKCARD", 1000);

        Assertions.assertThat(card.getPoint()).isEqualTo((int) (1000 + 1000 * 0.01));
        Assertions.assertThat(cardService.findCardNameById("xyz123")).isEqualTo("PARKCARD");
    }
}
