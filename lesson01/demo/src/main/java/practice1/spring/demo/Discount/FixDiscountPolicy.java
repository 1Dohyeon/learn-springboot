package practice1.spring.demo.Discount;

import practice1.spring.demo.Member.Grade;
import practice1.spring.demo.Member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; // 할인 금액: 1000원

    @Override
    public int discount(Member member, int price) { // grade가 VIP라면 discountFixAmount변수만큼 할인
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
