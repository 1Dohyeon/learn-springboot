package spring.practice01.demo;

import spring.practice01.demo.card.CardMemory;
import spring.practice01.demo.card.CardMemoryImpl;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.card.CardServiceImpl;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

public class AppConfig {
    public CardService cardService() {
        return new CardServiceImpl(
                new CardMemoryImpl(),
                new MemoryMemberImpl());
    }

    public MemoryMember memoryMember() {
        return new MemoryMemberImpl();
    }

    public CardMemory cardMemory() {
        return new CardMemoryImpl();
    }
}
