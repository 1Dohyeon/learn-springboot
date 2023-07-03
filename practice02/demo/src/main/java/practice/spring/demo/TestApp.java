package practice.spring.demo;

import practice.spring.demo.memberpack.Coupon;
import practice.spring.demo.memberpack.Member;
import practice.spring.demo.memberpack.MemberService;
import practice.spring.demo.orderpack.Order;
import practice.spring.demo.orderpack.OrderService;

public class TestApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        OrderService orderService = new OrderService();

        Member member1 = new Member(1L, "KIM", Coupon.A);
        Member member2 = new Member(4L, "PARK", Coupon.None);
        Member member3 = new Member(2L, "CHOI", Coupon.B);

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        Order order1 = orderService.createOrder(1L, "itemA", 10000);
        Order order2 = orderService.createOrder(4L, "itemB", 8000);
        Order order3 = orderService.createOrder(2L, "itemC", 9000);

        System.out.println("findMember = " + member1.getName());
        System.out.println("order = " + order1);

        System.out.println("findMember = " + member2.getName());
        System.out.println("order = " + order2);

        System.out.println("findMember = " + member3.getName());
        System.out.println("order = " + order3);
    }
}
