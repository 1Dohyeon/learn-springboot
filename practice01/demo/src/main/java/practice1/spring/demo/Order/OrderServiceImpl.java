package practice1.spring.demo.Order;

import practice1.spring.demo.Discount.DiscountPolicy;
import practice1.spring.demo.Discount.FixDiscountPolicy;
import practice1.spring.demo.Member.Member;
import practice1.spring.demo.Member.MemberRepository;
import practice1.spring.demo.Member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
