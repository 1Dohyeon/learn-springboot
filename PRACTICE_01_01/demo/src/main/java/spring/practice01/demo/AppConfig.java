package spring.practice01.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.practice01.demo.card.CardMemory;
import spring.practice01.demo.card.CardMemoryImpl;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.card.CardServiceImpl;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

@Configuration
public class AppConfig {
    @Bean
    public CardService cardService() {
        return new CardServiceImpl(
                new CardMemoryImpl(),
                new MemoryMemberImpl());
    }

    @Bean
    public MemoryMember memoryMember() {
        return new MemoryMemberImpl();
    }

    @Bean
    public CardMemory cardMemory() {
        return new CardMemoryImpl();
    }
}
