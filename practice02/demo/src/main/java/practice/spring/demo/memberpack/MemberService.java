package practice.spring.demo.memberpack;

public class MemberService {

    private final MemberRepository memberRepository = new MemberRepository();

    public void join(Member member) {
        memberRepository.save(member);
    };

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
