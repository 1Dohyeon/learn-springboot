package spring.practice01.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberImpl implements MemoryMember {
    private static Map<String, Member> store = new HashMap<>();

    // 저장된 메모리를 반환하는 메소드
    @Override
    public Map<String, Member> getMemoryMember() {
        return store;
    }

    // Map에 member를 저장하는 메소드
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

    // 저장된 메소드를 조회하는 메소드
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
