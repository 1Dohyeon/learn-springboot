package spring.practice01.demo.card;

import java.util.HashMap;
import java.util.Map;

// 카드 name을 key로 card 필드를 value로 데이터를 저장하는 메소드
public class CardMemoryImpl implements CardMemory {
    private static Map<String, Card> cardStore = new HashMap<>();

    // 저장된 메모리를 반환하는 메소드
    public Map<String, Card> getCardStore() {
        return cardStore;
    }

    // 메모리에 카드를 저장하는 메소드
    @Override
    public void saveCard(Card card) {
        if (cardStore.containsKey(card.getCardName())) { // 메모리에 똑같은 name의 카드가 존재할 경우
            System.out.println("이미 똑같은 cardName이 존재하여 데이터를 저장할 수 없습니다.");
        } else { // 메모리에 똑같은 name의 카드가 존재하지 않을 경우
            cardStore.put(card.getCardName(), card);
            card.setInCardMemory(true);
            System.out.println("cardStore에 카드가 저장 되었습니다.");
        }
    }
}
