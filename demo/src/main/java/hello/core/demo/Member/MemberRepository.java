package hello.core.demo.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
