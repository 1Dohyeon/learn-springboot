package practice.spring.demo.memberpack;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    public void save(Member member) {
        store.put(member.getId(), member); // memberId와 그 memberId를 가진 Member타입의 필드 member를 그대로 저장
    }

    public Member findById(Long memberId) {
        return store.get(memberId); // memberId를 파라미터로 받고, 그 id를 가진 Member타입의 필드 member를 그대로 반환
    }
}
