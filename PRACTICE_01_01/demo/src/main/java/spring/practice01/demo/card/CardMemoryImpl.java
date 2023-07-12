package spring.practice01.demo.card;

import java.util.HashMap;
import java.util.Map;

public class CardMemoryImpl implements CardMemory {
    private static Map<String, Card> cardStore = new HashMap<>();

    public Map<String, Card> getCardStore() {
        return cardStore;
    }

    @Override
    public void saveCard(Card card) {
        if (cardStore.containsKey(card.getCardName())) {
            System.out.println("이미 똑같은 cardName이 존재하여 데이터를 저장할 수 없습니다.");
        } else {
            cardStore.put(card.getCardName(), card);
            card.setInCardMemory(true);
            System.out.println("카드가 저장 되었습니다.");
        }
    }
}
