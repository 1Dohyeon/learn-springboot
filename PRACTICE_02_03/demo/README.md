# [스프링 #3] 스프링 컨테이너와 빈 등록, 빈 조회, 싱글톤

## 1. 스프링 컨테이너와 빈 등록

기존에 스프링 없이 만들었던 AppConfig 파일을 스프링 컨테이너로 등록시키려고 한다.

우선 각 클래스 파일들을 패키지로 분리해주었다.
그리고 스프링을 설치하고 저번 예제를 복사해서 적용시켰다.

### class AppConfig
```
package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.memory.Memory;
import com.example.demo.memory.MemoryImpl;
import com.example.demo.surgery.EyesSurgery;
import com.example.demo.surgery.Surgery;

@Configuration
public class AppConfig {
    @Bean
    public Surgery surgery() {
        return new EyesSurgery(
                memory());
        /*
         * return new NoseSurgery(
         * memory());
         */
    }

    @Bean
    public Memory memory() {
        return new MemoryImpl();
    }
}
```
위 코드대로 class 위에 **"@Configuration"** 를 붙여준다.
이는 AppConfig에 설정을 구성한다는 뜻이다.
그리고 각 메서드에 @Bean 을 붙여준다.
스프링 컨테이너에 스프링 빈으로 등록하게 된다.

### class Main
```
package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.person.Person;
import com.example.demo.surgery.Surgery;

public class Main {

    public static void main(String[] args) {
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Surgery surgery = ac.getBean(Surgery.class);

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        // surgery.plasticSurgery(철수);
        System.out.println("철수 코 = " + 철수.getnose());

        // 맹구의 코 성형수술
        System.out.println("맹구 코 = " + 맹구.getnose());
        // surgery.plasticSurgery(맹구);
        System.out.println("맹구 코 = " + 맹구.getnose());

        // 철수의 코 성형수술
        System.out.println("철수 눈 = " + 철수.getEyes());
        surgery.plasticSurgery(철수);
        System.out.println("철수 눈 = " + 철수.getEyes());

        // 맹구의 코 성형수술
        System.out.println("맹구 눈 = " + 맹구.getEyes());
        surgery.plasticSurgery(맹구);
        System.out.println("맹구 눈 = " + 맹구.getEyes());

        surgery.find();
    }
}
```

스프링 빈 등록 확인을 위해서 main 클래스를 수정해주었다.

기존에는 AppConfig appConfig = new AppConfig(); 코드와 같이 AppConfig 타입의 객체를 이용해서 생성자 주입을 하여 Surgery 타입의 객체를 불러올 수 있었다.

하지만 아래 코드를 이용하여 스프링 컨테이너를 생성한다.
그리고 getBean 메서드를 이용하여 AppConfig에 @Bean으로 등록되어 있는 빈 객체를 조회하여 가져온다.
이때 ()안에 Surgery 타입을 넣어서 Surgery 타입 빈 객체를 가져온다.
### class Main 일부
```
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
Surgery surgery = ac.getBean(Surgery.class);
```

---

## 2. 스프링 빈 조회

이제 main이 아닌 다른 방식으로 테스트 해보겠다.
스프링 빈 폴더를 만들었다면 main에 java말고 test파일이 있을 것이다.
그곳에 BeanTest.java 파일을 만들고 빈을 조회하는 테스트를 하였다.

### class BeanTest
```
package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.surgery.EyesSurgery;
import com.example.demo.surgery.Surgery;

public class BeanTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + " object=" +
                    bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" +
                        bean);
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        Surgery Surgery = ac.getBean("surgery",
                Surgery.class);
        Assertions.assertThat(Surgery).isInstanceOf(EyesSurgery.class);
    }
}
```
**"new AnnotationConfigApplicationContext(AppConfig.class);"** 를 통해서 스프링 컨테이너를 생성해주고, AppConfig.class 를 구성 정보로 지정했다.

위 코드의 **"@DisplayName("모든 빈 출력하기")"** 부분을 실행한다면 스프링 빈에 등록된 모든 빈이 출력된다.
**"ac.getBeanDefinitionNames()"** 가 스프링에 등록된 모든 빈 이름을 조회하는 역할을 해주기 때문이다.

하지만 이때는 스프링 내부에서 사용되는 빈도 조회에 포함된다.
그렇기에 **"@DisplayName("애플리케이션 빈 출력하기")"** 에서 **"getRole()"** 을 이용하여 구분하였다.
- ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
- ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

그리고 스프링 빈은 getBean을 통해서 **타입** 또는 **빈 이름, 타입**, 두가지 방법으로 조회할 수 있다.

```
만약 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 어떻게 될까?
당연하게도 오류가 발생한다, 이때는 빈 이름을 지정해야된다.
```
@Bean을 등록할 때 **@Bean(" ")** " " 안에 이름을 따로 지정할 수 있다.
하지만 그냥 기존 타입을 소문자로 한 기본 이름을 사용하는 것이 대체적으로 좋다.

- ac.getBeansOfType() 을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.

아래 코드는 상속관계에서의 스프링 빈 조회 방법이다.
### class BeanTest2
```
package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.memory.Memory;
import com.example.demo.memory.MemoryImpl;
import com.example.demo.surgery.EyesSurgery;
import com.example.demo.surgery.NoseSurgery;
import com.example.demo.surgery.Surgery;

public class BeanTest2 {
    // 예제 컨테이너 빈
    @Configuration
    static class TestConfig {
        @Bean
        public Memory memory() {
            return new MemoryImpl();
        }

        @Bean
        public Surgery noseSurgery() {
            return new NoseSurgery(memory());
        }

        @Bean
        public Surgery eyesSurgery() {
            return new EyesSurgery(memory());
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(Surgery.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        Surgery rateDiscountPolicy = ac.getBean("eyesSurgery",
                Surgery.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(EyesSurgery.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        EyesSurgery bean = ac.getBean(EyesSurgery.class);
        Assertions.assertThat(bean).isInstanceOf(EyesSurgery.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, Surgery> beansOfType = ac.getBeansOfType(Surgery.class);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" +
                    beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" +
                    beansOfType.get(key));
        }
    }
}
```

---
'''
**AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);** 까지 작성해야하니 뭔가 더 복잡해진 것 같다.
그런데 왜 스프링을 사용해야되는 것일까?
여러가지 장점이 있지만 그 중 하나는 바로 싱글톤이다.
'''

## 3. 싱글톤이란?

보통 웹 애플리케이션 서비스에서 여러 고객이 동시에 요청을 한다.
스프링 없이 만들었던 AppConfig는 고객이 요청을 할 때마다 객체를 새로 생성하였다.

따라서 메모리 낭비가 점점 심해지게 된다.
위 문제점을 해결하기 위해 싱글톤 패턴을 이용하는 것이다.
싱글톤 패턴이란 해당 객체가 딱 1번만 생성되고, 공유되며 재활용되는 방식을 의미한다.

즉, 싱글톤 패턴은 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 패턴이다.

싱글톤 패턴도 구현하는 코드가 많이 들어가는 점, 테스트하기 어렵다는 점 그리고 유연성이 떨어진다는 단점 등 여러가지 문제점들이 있다.
하지만 이런 문제점을 해결하고, 객체 인스턴스를 싱글톤 패턴으로 관리하는 것이 바로 스프링 컨테이너이다.
여러 이유도 있지만, 위와 같은 이유로 스프링을 사용한다.

-----



