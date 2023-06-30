package practice1.spring.demo.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
