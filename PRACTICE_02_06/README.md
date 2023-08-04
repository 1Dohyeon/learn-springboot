> 스프링 공부 시리즈는 인프런 김영한님의 강의를 수강하였고, 이를 바탕으로 복습한 내용입니다.

## 1. 스코프란?
스코프는 단어 뜻 그대로 빈이 존재할 수 있는 범위를 뜻한다.

스프링은 다양한 스코프를 지원한다.
> **1. 싱글톤:** 스코프의 기본형이며 스프링 컨테이너의 시작과 종료까지 계속 유지되는 가장 넓은 범위의 스코프이다.
**2. 프로토타입:** 프로토타입의 빈의 생성과 의존관계 주입까지만 관여하는 매우 짧은 범위의 스코프이다.
**3. 웹 관련 스코프**
**	- request:** 웹 요쳥이 들어오고 나갈때 까지 유지되는 스코프이다.
**    - session:** 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프이다.
**    - application:** 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다.
>
> ...
>
>위와 같이 여러가지가 존재함.

---

## 2. 프로토타입이란?

스코프를 지정할 때 싱글톤은 기본형이기에 따로 설정을 안해도 되지만 프로토타입의 경우는 아래 코드처럼 작성해야한다.
```
@Scope("prototype")
@Component
public class Bean {}
```

프로토타입은 싱글톤과 달리 클라이언트가 스코프 빈을 스프링 컨테이너에 요청한 경우, 프로토 타입 빈을 생성하고 의존관계를 주입 후 클라이언트에 반환을 하고 더 이상 관리하지 않는다.
스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다.

즉, 계속 같은 요청이 들어오면 싱글톤과 다르게 새로운 프로토타입의 빈을 생성한다는 의미이다.

### 프로토타입 예제 코드
```
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        
        ac.close(); // 종료
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

```
### 실행 결과
```
find prototypeBean1
PrototypeBean.init
find prototypeBean2
PrototypeBean.init
prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@3829ac1
prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@4baf352a
```
실행 결과에서 빈을 조회한 부분을 보면 완전히 다른 스프링 빈이 생성된 것을 알 수 있고, 초기화도 2번 실행된 것을 알 수 있다.

프로토타입 빈은 초기화까지만 관여하고 더 이상 관여하지 않기에 스프링 컨테이너가 종료될 때 @PreDestroy의 종료 메서드가 실행되지 않는다.

### 스프링에서 프로토타입을 언제 사용할까?
> 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사용하면 된다. 그런데 실무에서 웹 애플리케이션을 개발해보면, 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에 프로토타입 빈을 직접적으로 사용하는 일은 매우 드물다.

---

## 3. 싱글톤 빈과 함께 사용시 문제점
스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 

그런데 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만, 싱글톤 빈과 함께 계속 유지되는 것이 문제다.
즉, 프로토타입 빈은 주입 시점에만 생성되게 된다.

프로토타입은 의존관계 주입시에만 프로토타입 빈이 생성되는 것이 아닌, 사용할 때마다 새로 생성되는 것 때문에 사용하게 된다.

이러한 문제점을 고치기 위해서 싱글톤과 함께 사용시에 Provider로 문제를 해결할 수 있다.

---

## 4. Provider

Provider를 사용하는 방법으로는 아래와 같이 여러 방법들이 있다.
> 1. 스프링 컨테이너에 요청
2. ObjectFactory, ObjectProvide
3. jakarta.inject.Provider

1, 2 번째 방법은 여러 단점들 때문에 3번째 방법을 주로 이용한다.

이 방법을 이용하기 위해서는 아래 코드처럼 **jakarta.inject:jakarta.inject-api:2.0.1** 라이브러리를 gradle에 추가해야 한다.(스프링부트 3.0 이상)
```
	// provider
	implementation 'jakarta.inject:jakarta.inject-api:2.0.1'
```
### 테스트 코드
```
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest3 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonWithPrototypeTest2.ClientBean.class,
                SingletonWithPrototypeTest2.PrototypeBean.class);
        SingletonWithPrototypeTest2.ClientBean clientBean1 = ac.getBean(SingletonWithPrototypeTest2.ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        SingletonWithPrototypeTest2.ClientBean clientBean2 = ac.getBean(SingletonWithPrototypeTest2.ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }
    
    static class ClientBean {
        @Autowired
        private Provider<PrototypeBean> provider;
        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

```
위 코드를 요약하자면 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환을 한다.
이때 해당 빈의 count값은 0이고, 클라이언트는 조회한 프로토타입 빈에 count필드를 +1한다.
클라이언트는 2번 빈을 조회했고, 그 빈은 프로토타입이므로 각각 다른 빈이다.

즉, count를 두번 늘릴때 count의 결과값은 각 빈마다 똑같이 1이어야한다.

두개 다 1인지 테스트를 해보았고, 다행이도 잘 통과하였다.
### 테스트 코드 일부
```
	static class ClientBean {
        @Autowired
        private Provider<PrototypeBean> provider;
        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
```
라이브러리에 등록한 provider의 get()메서드를 이용하여 항상 새로운 프로토타입 빈이 생성되도록 한다.

---

아직 공부 중이라 틀린 부분이 있을 수도 있습니다.
이 포스팅은 제가 공부한 것을 정리하기 위함이며 올바르지 않은 정보가 전달될 수도 있습니다.
혹시 오류가 있는 부분이 있다면 알려주시면 감사하겠습니다.
[github 링크](https://github.com/1Dohyeon/Study-spring/tree/master/PRACTICE_02_06/core/src/test/java/hello/core/scope)