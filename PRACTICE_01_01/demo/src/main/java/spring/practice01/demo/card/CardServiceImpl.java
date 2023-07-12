package spring.practice01.demo.card;

import java.util.HashMap;
import java.util.Map;

import spring.practice01.demo.member.Member;

public class CardServiceImpl implements CardService {

    private static Map<String, String> membersCard = new HashMap<>();
    private static Map<String, String> cardsMember = new HashMap<>();
    CardMemory cardMemory = new CardMemoryImpl();

    @Override
    public void saveCard(Card card, Member member) {
        if (cardsMember.containsKey(member.getId())) {
            System.out.println("회원님의 카드가 존재합니다.");
        } else {
            cardsMember.put(card.getCardName(), member.getId());
            membersCard.put(member.getId(), card.getCardName());
            card.setInCardMemory(true);
            System.out.println("카드가 저장 되었습니다.");
        }
    }

    @Override
    public String findCardNameById(String id) {
        if (membersCard.containsKey(id)) {
            System.out.println("card name = " + membersCard.get(id));
            return membersCard.get(id);
        } else {
            System.out.println("해당 회원의 카드는 존재하지 않습니다.");
            return null;
        }
    }

    @Override
    public Card createCard(Member member, String cardName, int point) {
        if (member.getInMemory() == true) {
            if (member.getHaveCard() == true) {
                System.out.println("이미 카드를 소유 중입니다.");
                return null;
            } else {
                if (cardsMember.containsKey(cardName)) {
                    System.out.println("이미 존재하는 카드 이름입니다. 다른 카드 이름을 설정해주세요.");
                    return null;
                } else {
                    Card card = new Card(cardName, point);
                    cardMemory.saveCard(card);
                    saveCard(card, member);
                    System.out.println("카드를 만들었습니다, cardname = " + cardName);

                    member.setYourCardName(cardName);
                    member.setHaveCard(true);
                    return card;
                }
            }
        } else {
            System.out.println("회원가입을 먼저 해주세요.");
            return null;
        }
    }
}
