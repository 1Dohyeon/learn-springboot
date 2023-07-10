package practice1.spring.demo.Discount;

import org.springframework.stereotype.Component;

import practice1.spring.demo.Member.Grade;
import practice1.spring.demo.Member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
