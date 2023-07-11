package practice1.spring.demo.singleton;

import java.beans.Transient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice1.spring.demo.AppConfig;
import practice1.spring.demo.Member.MemberService;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 검증 memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new SingletonService();
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 검증 memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);

        // 스프링 컨테이너가 알아서 싱글톤으로 만들어줌
        // 위 pureTest의 appConfig에서 불러온다면, 객체를 계속 생성하지만, 스프링 컨테이너를 사용한다면,
        // 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.
        // 그렇기에 코드고 길어진다해도 스프링 컨테이너를 이용하는 것이다.
        // 코드가 길어지는 것이지 복잡해지는 것도 아니고, 많이 길어지는 것도 아니고 한두줄이니까 스프링 쓰자...
        // 스프링을 사용하는 이유 첫번째가 싱글톤!
    }
}
