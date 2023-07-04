package practice1.spring.demo;

import practice1.spring.demo.Discount.FixDiscountPolicy;
import practice1.spring.demo.Member.MemberService;
import practice1.spring.demo.Member.MemberServiceImpl;
import practice1.spring.demo.Member.MemoryMemberRepository;
import practice1.spring.demo.Order.OrderService;
import practice1.spring.demo.Order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}
