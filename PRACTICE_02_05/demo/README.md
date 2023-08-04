# [스프링 #5]

> 스프링 공부 시리즈는 인프런 김영한님의 강의를 수강하였고, 이를 바탕으로 복습한 내용입니다.

## 콜백이란??
콜백(Callback)은 프로그래밍에서 함수 또는 메서드를 인자(argument)로 다른 함수에 전달하고, 이를 통해 어떤 이벤트가 발생했을 때 해당 함수가 호출되도록 하는 기법이다.

쉽게 말해, 콜백은 다른 함수의 동작 중에 원하는 코드를 끼워넣는 방법 중 하나이다.

일반적으로 프로그래밍에서 함수는 자신의 동작을 수행하고 결과를 반환하지만 콜백을 사용하면 함수가 동작 중에 다른 함수를 호출하면서, 해당 함수에게 특정 이벤트가 발생했을 때 실행할 코드를 맡길 수 있다. 

이는 비동기적인 프로그래밍이나 이벤트 기반 프로그래밍에서 많이 활용되며, 대표적으로 데이터베이스 커넥션 풀과 네트워크 소켓 상황에서 사용된다.

이 상황에서는 애플리케이션 시작 시점에 객체의 초기화와 애플리케이션 종료 시점에 연결을 모두 종료하는 작업이 필요하다.

스프링은 객체 생성 이후 의존관계를 주입하고, 그 다음에서야 필요한 데이터를 사용할 수 있다. 그렇기에 초기화 작업은 의존관계가 끝난 이후에 호출해야한다.

하지만 의존관계 주입이 전부 완료된 시점을 찾기가 힘들다.

이때 스프링은 콜백 메서드를 통해서 초기화 시점 및 종료 시점도 알려준다.
크게 아래와 같은 세가지 방법이 있다.

> 1. 인터페이스(InitializingBean, DisposableBean)
> 2. 설정 정보에 초기화 메서드, 종료 메서드 지정
> 3. @PostConstruct, @PreDestroy 애노테이션 지원

1번 방법은 스프링 전용 인터페이스에만 의존한다는 단점이 있다. 그렇기에 메서드의 이름을 변경할 수 없고 외부 라이브러리에 적용할 수 없다는단점이 있어 잘 사용하지 않는다. 

2번 방법은 아래 코드처럼 빈 등록을 할 때 메서드를 지정하는 방식이다.
```
@Bean(initMethod = "init", destroyMethod = "close")
```
이 방법을 이용하면 init 메서드는 초기화 시점을 알려주고, close메서드는 종료 시점을 알려준다.
또한 이방법은 외부 라이브러리에도 초기화와 종료 메서드를 적용할 수 있다.

3번째 방법인 **@PostConstruct, @PreDestroy 애노테이션 지원** 방법은 스프링에서 권장하는 방법이다. 애노테이션 하나로 모두 처리되기에 편리하다.

우선 아래 코드 예제는 애노테이션이 없을 경우이다.
### 코드 예제: class CallBack
```
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CallBack {
    private String id;
    public CallBack() {
        System.out.println("생성자 호출, url = " + id);
        connect();
        call();
    }
    public void setId(String id) {
        this.id = id;
    }
    // 시작 콜백
    public void connect() {
        System.out.println("connect: " + id);
    }
    public void call() {
        System.out.println("call: " + id);
    }
    // 종료 콜백
    public void disConnect() {
        System.out.println("close: " + id);
    }
}
```
### 코드 예제 테스트: class CallBackTest
```
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class CallBackTest {
    @Test
    public void callBackTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        CallBack callBack = ac.getBean(CallBack.class);
        ac.close(); //스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
    }
    @Configuration
    static class LifeCycleConfig {
        @Bean
        public CallBack callBack() {
            CallBack callBack = new CallBack();
            callBack.setId("KIM");
            return callBack;
        }
    }
}

```
### 테스트 결과
```
생성자 호출, id = null
connect: null
call: null
```
위 결과를 보면 id에는 어떠한 값도 없이 connect 메서드가 호출된다.
id를 setId() 메서드를 통해서만 받기 때문이다.
그렇다면 connect를 의존관계 주입이 완료된 후에 connect 메서드를 호출해야한다.

아래 코드는 애노테이션을 추가한 코드예제이다.
### 코드 예제: class CallBack
```
package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CallBack {
    private String id;
    public CallBack() {
        System.out.println("생성자 호출, id = " + id);
        connect();
        call();
    }
    public void setId(String id) {
        this.id = id;
    }
    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + id);
    }
    public void call() {
        System.out.println("call: " + id);
    }
    //서비스 종료시 호출
    public void disConnect() {
        System.out.println("close: " + id);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call();
    }
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
```
### 테스트 결과
```
생성자 호출, id = null
connect: null
call: null
NetworkClient.init		<- 초기화 시점을 콜백해서 알려줌 
connect: KIM
call: KIM
NetworkClient.close		<- 종료 시점을 콜백해서 알려줌
close: KIM
```
이렇게 **@PostConstruct, @PreDestroy** 을 이용하여 의존관계 주입이 끝난 시점과 애플리케이션 종료 시점을 알 수 있게 되었다.

위 코드에서 알 수 있듯이, **@PostConstruct** 애노테이션을 붙인 메서드는 의존관계 주입이 완료된 시점에서 메서드가 실행될 수 있도록 해주고, **@PreDestroy** 애노테이션을 붙인 메서드는 애플리케이션 종료 시점에 메서드가 실행될 수 있도록 해준다.

---

아직 공부 중이라 틀린 부분이 있을 수도 있습니다.
이 포스팅은 제가 공부한 것을 정리하기 위함이며 올바르지 않은 정보가 전달될 수도 있습니다.
혹시 오류가 있는 부분이 있다면 알려주시면 감사하겠습니다.
[github 링크](https://github.com/1Dohyeon/Study-spring/tree/master/PRACTICE_02_06/core/src/test/java/hello/core/lifecycle)
