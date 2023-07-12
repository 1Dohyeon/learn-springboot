package spring.practice01.demo.card;

import java.util.Map;

public interface CardMemory {
    Map<String, Card> getCardStore();

    void saveCard(Card card);
}
