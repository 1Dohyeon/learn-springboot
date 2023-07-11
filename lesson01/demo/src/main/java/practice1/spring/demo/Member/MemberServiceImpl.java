package practice1.spring.demo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    /*
     * private final MemberRepository memberRepository = new
     * MemoryMemberRepository();
     * Day1,2 기존 코드
     */
    private MemberRepository memberRepository;

    // 생성자 -> MemberServiceImpl 타입의 필드 memberRepository만 이용. 즉 인터페이스에만 의존
    // 생성자 주입
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // day6, 싱글톤을 확인하기 위한 반환 함수, 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
