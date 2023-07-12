package spring.practice01.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberImpl implements MemoryMember {
    private static Map<String, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        if (store.containsKey(member.getId())) {
            System.out.println("이미 ID가 존재하여 데이터를 저장할 수 없습니다.");
        } else {
            store.put(member.getId(), member);
            member.setInMemory(true);
            System.out.println("데이터가 저장 되었습니다.");
        }
    }

    @Override
    public Member findById(String id) {
        if (store.containsKey(id)) {
            System.out.println("ID = " + id + ", ID 소유자 이름 = " + store.get(id).getName());
            return store.get(id);
        } else {
            System.out.println("데이터가 존재하지 않습니다.");
            return null;
        }
    }
}
