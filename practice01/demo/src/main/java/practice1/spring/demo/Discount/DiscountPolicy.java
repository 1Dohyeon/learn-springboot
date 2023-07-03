package practice1.spring.demo.Discount;

import practice1.spring.demo.Member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */

    int discount(Member member, int price);
}
