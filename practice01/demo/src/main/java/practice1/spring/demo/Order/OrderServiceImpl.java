package practice1.spring.demo.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import practice1.spring.demo.Discount.DiscountPolicy;
import practice1.spring.demo.Discount.FixDiscountPolicy;
import practice1.spring.demo.Discount.RateDiscountPolicy;
import practice1.spring.demo.Member.Member;
import practice1.spring.demo.Member.MemberRepository;
import practice1.spring.demo.Member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {
    // private final MemberRepository memberRepository = new
    // MemoryMemberRepository(); day1,2 코드
    /* private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); */ // day1,2 코드
    /* private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); */ // 할인정책 변경

    /*
     * 위 코드의 문제점:
     * 추상 interface에만 의존해야하는데 구체 클래스(FixDiscountPolicy, RateDiscountPolicy)에도 의존하는
     * 형태이다.
     * DIP 위반
     */

    // 문제점 고친 코드(추상클래스에만 의존하게 된다. 할인정책 변경시 코드를 변경할 필요가 없다.)
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 생성자
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
