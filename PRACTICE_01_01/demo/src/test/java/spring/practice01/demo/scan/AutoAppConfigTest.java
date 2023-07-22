package spring.practice01.demo.scan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.practice01.demo.AutoAppConfig;
import spring.practice01.demo.card.CardService;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AutoAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan() {
        CardService cardService = ac.getBean(CardService.class);
        assertThat(cardService).isInstanceOf(CardService.class);
    }
}
