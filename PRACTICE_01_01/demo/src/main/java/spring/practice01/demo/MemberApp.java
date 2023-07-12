package spring.practice.demo;

import spring.practice.demo.member.Member;
import spring.practice.demo.member.MemoryMember;
import spring.practice.demo.member.MemoryMemberImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemoryMember memoryMember = new MemoryMemberImpl();

        Member member1 = new Member("abc123", "qwer1234", "Kim");
        Member member2 = new Member("xyz123", "qwer1234", "Park");
        Member member3 = new Member("xyz123", "abcd1234", "Lee");

        memoryMember.save(member1);
        memoryMember.save(member2);
        memoryMember.save(member3);

        memoryMember.findById("abc123");
        memoryMember.findById("xyz123");
        memoryMember.findById("qwer1234");
    }
}
