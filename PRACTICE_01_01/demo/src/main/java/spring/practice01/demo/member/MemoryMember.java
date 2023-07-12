package spring.practice01.demo.member;

public interface MemoryMember {
    void save(Member member);

    Member findById(String memberId);
}
