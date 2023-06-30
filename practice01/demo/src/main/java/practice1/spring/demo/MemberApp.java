package practice1.spring.demo;

import practice1.spring.demo.Member.Grade;
import practice1.spring.demo.Member.Member;
import practice1.spring.demo.Member.MemberService;
import practice1.spring.demo.Member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
