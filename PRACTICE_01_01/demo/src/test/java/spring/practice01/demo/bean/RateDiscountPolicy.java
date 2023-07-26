package spring.practice01.demo.bean;

import org.springframework.stereotype.Component;

import spring.practice01.demo.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {
        return 2000;
    }
}
