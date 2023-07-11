package practice1.spring.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import practice1.spring.demo.Discount.DiscountPolicy;
import practice1.spring.demo.Discount.FixDiscountPolicy;
import practice1.spring.demo.Discount.RateDiscountPolicy;
import practice1.spring.demo.Member.MemberRepository;
import practice1.spring.demo.Member.MemberService;
import practice1.spring.demo.Member.MemberServiceImpl;
import practice1.spring.demo.Member.MemoryMemberRepository;
import practice1.spring.demo.Order.OrderService;
import practice1.spring.demo.Order.OrderServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository()");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy(); // <- 할인 정책 변경시에 이 부분만 수정하면 됨
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService()");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // @Bean memberService -> new MemoryMemberRepository();
    // @Bean orderService -> memberRepository() -> new MemoryMemberRepository();

    // 싱글톤이 깨지는 거 아닐까? -> 테스트 해보자

    // memberRepository가 3번 호출되어야하지만 싱글톤인 스프링 컨테이너는 한번만 호출하고 재사용함.

}
