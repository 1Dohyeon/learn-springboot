package hello.core.demo.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
