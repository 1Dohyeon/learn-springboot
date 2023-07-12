package spring.practice.demo.member;

public interface MemoryMember {
    void save(Member member);

    Member findById(String memberId);
}
