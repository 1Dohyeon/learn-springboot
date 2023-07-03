package practice1.spring.demo;

import practice1.spring.demo.Member.Grade;
import practice1.spring.demo.Member.Member;
import practice1.spring.demo.Member.MemberService;
import practice1.spring.demo.Member.MemberServiceImpl;
import practice1.spring.demo.Order.Order;
import practice1.spring.demo.Order.OrderService;
import practice1.spring.demo.Order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("findMember = " + member.getName());
        System.out.println("order = " + order);
    }
}
