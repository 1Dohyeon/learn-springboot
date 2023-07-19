package spring.practice01.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.practice01.demo.AppConfig;
import spring.practice01.demo.card.CardService;

public class SingletonTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 1. 조회: 호출할 때 마다 같은 객체를 반환
        CardService cardService1 = ac.getBean("cardService",
                CardService.class);
        // 2. 조회: 호출할 때 마다 같은 객체를 반환
        CardService cardService2 = ac.getBean("cardService",
                CardService.class);
        // 참조값이 같은 것을 확인
        System.out.println("cardService1 = " + cardService1);
        System.out.println("cardService2 = " + cardService2);
        // memberService1 == memberService2
        Assertions.assertThat(cardService1).isSameAs(cardService2);
    }
}
