package spring.practice01.demo.memberTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import spring.practice01.demo.member.Member;
import spring.practice01.demo.member.MemoryMember;
import spring.practice01.demo.member.MemoryMemberImpl;

public class MemberTest {
    @Test
    void testing() {
        MemoryMember memoryMember = new MemoryMemberImpl();
        Member member = new Member("abc123", "qwer1234", "Kim");

        memoryMember.save(member);
        Member findMember = memoryMember.findById("abc123");

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
