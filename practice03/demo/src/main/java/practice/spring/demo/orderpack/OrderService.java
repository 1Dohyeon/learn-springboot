package practice.spring.demo.orderpack;

import practice.spring.demo.discountpack.DiscountPolicy;
import practice.spring.demo.memberpack.Member;
import practice.spring.demo.memberpack.MemberRepository;

public class OrderService {
    private final MemberRepository memberRepository = new MemberRepository();
    private final DiscountPolicy discountPolicy = new DiscountPolicy();

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
