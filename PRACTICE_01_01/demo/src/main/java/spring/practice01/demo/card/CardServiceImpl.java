package spring.practice01.demo.card;

import java.util.HashMap;
import java.util.Map;

import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;

public class CardServiceImpl implements CardService {

    private static Map<String, String> membersCard = new HashMap<>();
    private final CardMemory cardMemory;
    private final MemoryMember memoryMember;

    public CardServiceImpl(CardMemory cardMemory, MemoryMember memoryMember) {
        this.cardMemory = cardMemory;
        this.memoryMember = memoryMember;
    }

    // 카드 정보 저장 메소드
    @Override
    public void saveCard(Card card, Member member) {
        // 이미 회원이 카드를 만들었을 경우
        if (membersCard.containsKey(member.getId())) {
            System.out.println("회원님의 카드가 존재합니다.");
        } else { // 회원이 카드를 만들지 않았을 경우
            membersCard.put(card.getCardName(), member.getId());
            System.out.println("membersCard에 카드가 저장 되었습니다.");
        }
    }

    // Member id를 통해 카드 name을 찾는 메소드
    @Override
    public String findCardNameById(String id) {
        /*
         * 방법 1:
         * 
         * // membersCard의 value값을 탐색하여 id와 같은 값이 있을 경우 그 value값의 key를 반환
         * for (Map.Entry<String, String> entry : membersCard.entrySet()) {
         * if (entry.getValue().equals(id)) {
         * System.out.println("card name = " + entry.getKey());
         * return entry.getKey();
         * }
         * }
         * System.out.println("해당 회원의 카드는 존재하지 않습니다.");
         * return null;
         */

        // 방법2: member 메모리를 바로 가져와서 회원 카드 이름을 return 함.
        Member member = memoryMember.findById(id);
        return member.getYourCardName();
    }

    // 카드 객체를 만드는 메소드
    @Override
    public Card createCard(Member member, String cardName, int point) {
        // 회원가입이 된 경우 카드를 만들 자격이 부여됨
        if (member.getInMemory() == true) {
            if (member.getHaveCard() == true) {
                // 회원이 카드를 가지고 있는 경우 생성이 안됨.
                System.out.println("이미 카드를 소유 중입니다.");
                return null;
            } else { // 회원이 카드를 가지고 있지 않은 경우

                if (membersCard.containsKey(cardName)) {
                    // 회원이 카드를 만들 수는 있지만 이미 카드 이름이 존재할 경우
                    System.out.println("이미 존재하는 카드 이름입니다. 다른 카드 이름을 설정해주세요.");
                    return null;
                } else { // 회원이 카드 만들기를 성공했을 경우
                    Card card = new Card(cardName, point); // 카드 객체 생성
                    cardMemory.saveCard(card); // 카드를 카드 메모리에 저장
                    saveCard(card, member); // 카드를 카드-회원 메모리에 저장
                    System.out.println("카드를 만들었습니다, cardname = " + cardName);

                    // 메모리 저장여부, 회원이 카드를 사유했는지의 여부,
                    // 회원이 소유한 카드 이름를 설정
                    card.setInCardMemory(true);
                    member.setHaveCard(true);
                    member.setYourCardName(cardName);

                    return card;
                }
            }
        } else { // 회원가입이 안 된 경우
            System.out.println("회원가입을 먼저 해주세요.");
            return null;
        }
    }
}
