package practice.spring.demo.discountpack;

import practice.spring.demo.memberpack.Coupon;
import practice.spring.demo.memberpack.Member;

public class DiscountPolicy {
    private int discountAmout = 3000;

    public int discount(Member member, int price) {
        Coupon coupon = member.getCoupon();

        if (coupon == Coupon.A) {
            return (int) (price * 0.1);
        } else if (coupon == Coupon.B) {
            return discountAmout;
        } else {
            return 0;
        }
    }
}
