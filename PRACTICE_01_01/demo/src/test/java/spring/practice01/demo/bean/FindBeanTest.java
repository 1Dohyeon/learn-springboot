package spring.practice01.demo.bean;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.practice01.demo.card.CardMemory;
import spring.practice01.demo.card.CardMemoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class FindBeanTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        // MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(CardMemory.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        CardMemory cardMemory = ac.getBean("cardMemory1", CardMemory.class);
        assertThat(cardMemory).isInstanceOf(CardMemory.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, CardMemory> beansOfType = ac.getBeansOfType(CardMemory.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public CardMemory cardMemory1() {
            return new CardMemoryImpl();
        }

        @Bean
        public CardMemory cardMemory2() {
            return new CardMemoryImpl();
        }
    }

    ///////////////////// 스프링 빈 조회 - 상속 관계

    /*
     * @Test
     * 
     * @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
     * void findBeanByParentTypeDuplicate() {
     * // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
     * assertThrows(NoUniqueBeanDefinitionException.class, () ->
     * ac.getBean(DiscountPolicy.class));
     * }
     * 
     * @Test
     * 
     * @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
     * void findBeanByParentTypeBeanName() {
     * DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",
     * DiscountPolicy.class);
     * assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
     * }
     * 
     * @Test
     * 
     * @DisplayName("특정 하위 타입으로 조회")
     * void findBeanBySubType() {
     * RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
     * assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
     * }
     * 
     * @Test
     * 
     * @DisplayName("부모 타입으로 모두 조회하기")
     * void findAllBeanByParentType() {
     * Map<String, DiscountPolicy> beansOfType =
     * ac.getBeansOfType(DiscountPolicy.class);
     * assertThat(beansOfType.size()).isEqualTo(2);
     * for (String key : beansOfType.keySet()) {
     * System.out.println("key = " + key + " value=" +
     * beansOfType.get(key));
     * }
     * }
     * 
     * @Test
     * 
     * @DisplayName("부모 타입으로 모두 조회하기 - Object")
     * void findAllBeanByObjectType() {
     * Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
     * for (String key : beansOfType.keySet()) {
     * System.out.println("key = " + key + " value=" +
     * beansOfType.get(key));
     * }
     * }
     * 
     * @Configuration
     * static class TestConfig {
     * 
     * @Bean
     * public DiscountPolicy rateDiscountPolicy() {
     * return new RateDiscountPolicy();
     * }
     * 
     * @Bean
     * public DiscountPolicy fixDiscountPolicy() {
     * return new FixDiscountPolicy();
     * }
     * }
     */
}
