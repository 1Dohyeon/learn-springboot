package spring.practice01.demo.card;

import java.util.Map;

public interface CardMemory {
    Map<String, Card> getCardStore();

    // 카드 name을 key로 card 필드를 value로 데이터를 저장하는 메소드
    void saveCard(Card card);
}
