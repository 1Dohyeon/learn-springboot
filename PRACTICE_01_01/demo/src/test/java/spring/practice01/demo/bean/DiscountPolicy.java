package spring.practice01.demo.bean;

import spring.practice01.demo.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
