package practice1.spring.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice1.spring.demo.AppConfig;
import practice1.spring.demo.Member.MemberRepository;
import practice1.spring.demo.Member.MemberServiceImpl;
import practice1.spring.demo.Order.OrderServiceImpl;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1: memberService -> memberRepository = " + memberRepository1);
        System.out.println("memberRepository2: orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
        // 세개 다 같다. 같은 instance가 공유되어있다.
    }
}
