package practice1.spring.demo;

import practice1.spring.demo.Discount.DiscountPolicy;
import practice1.spring.demo.Discount.FixDiscountPolicy;
import practice1.spring.demo.Member.MemberRepository;
import practice1.spring.demo.Member.MemberService;
import practice1.spring.demo.Member.MemberServiceImpl;
import practice1.spring.demo.Member.MemoryMemberRepository;
import practice1.spring.demo.Order.OrderService;
import practice1.spring.demo.Order.OrderServiceImpl;

public class AppConfig {

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy(); // <- 할인 정책 변경시에 이 부분만 수정하면 됨
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}
