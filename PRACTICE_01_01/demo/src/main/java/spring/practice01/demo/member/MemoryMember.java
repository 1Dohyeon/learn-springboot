package spring.practice01.demo.member;

import java.util.Map;

public interface MemoryMember {
    // 메모리에 member 정보를 저장하는 메소드
    void save(Member member);

    // id를 통해 저장된 저장된 value값을 반환하는 메소드
    Member findById(String memberId);

    // 저장된 메모리를 반환하는 메소드
    Map<String, Member> getMemoryMember();
}
