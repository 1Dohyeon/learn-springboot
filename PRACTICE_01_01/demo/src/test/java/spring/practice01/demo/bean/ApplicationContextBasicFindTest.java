package spring.practice01.demo.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.practice01.demo.AppConfig;
import spring.practice01.demo.card.CardService;
import spring.practice01.demo.card.CardServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        CardService cardService = ac.getBean("cardService", CardService.class);
        assertThat(cardService).isInstanceOf(CardServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType() {
        CardService cardService = ac.getBean(CardService.class);
        assertThat(cardService).isInstanceOf(CardServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        CardServiceImpl cardService = ac.getBean("cardService",
                CardServiceImpl.class);
        assertThat(cardService).isInstanceOf(CardServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        // ac.getBean("xxxxx", MemberService.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", CardService.class));
    }
}
