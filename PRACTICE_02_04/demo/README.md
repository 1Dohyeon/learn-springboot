# [스프링 #4] 컴포넌트 스캔과 의존관계 자동 주입

기존에는 AppConfig에 bean을 등록하여 스프링 빈을 적용시켰다.
하지만 등록해야할 빈이 많아진다면 하나하나 전부 @Bean으로 등록시키기에는 힘들어질 것이다.

그래서 스프링은 자동으로 스프링 빈을 등록해주는 컴포넌트 스캔과, 의존관계를 자동으로 주입하는 @Autowired 라는 기능을 제공해준다.

## 1. 컴포넌트 스캔

### class AutoAppConfig
```
package com.example.demo.surgery;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 컴포넌트 스캔을 사용하려면 먼저 @ComponentScan 을 설정 정보에 붙여주면 됨.
// @Bean 등록을 안해도됨.
@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
```
우선 자동으로 스프링 빈을 등록할 수 있도록 새로운 AutoAppConfig 클래스 파일을 만들어주었다.
컴포넌트 스캔을 사용하기 위해 위 코드처럼 @ComponentScan 을 설정 정보에 붙여주면 끝이다.

기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없는 것을 알 수 있다.

**"@ComponentScan"** 는 말 그대로 **"@Component"** 가 붙은 class를 스캔하여 스프링 빈으로 자동으로 등록하게 해주는 것이다.

```
excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
```

추가로 위 코드 부분은 ComponentScan에서 지정될 대상을 필터링 시킬 클래스를 지정하는 방법이다.
excludeFilters의 반대로 컴포넌트 스캔 대상을 추가할 수도 있다.
'''
includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.
'''

이제 **"@ComponentScan"** 가 다른 클래스들을 스캔하여 스프링 빈으로 등록시키게 하기 위해서 아래 클래스들에 **"@Component"** 를 추가시켰다.

### class MemoryImpl
```
package com.example.demo.memory;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MemoryImpl implements Memory {
    private static ArrayList<String> store = new ArrayList<String>();

    @Override
    public void save(String kind) {
        store.add(kind);
    }

    @Override
    public ArrayList<String> getMemory() {
        return store;
    }
}
```
### class EyesSurgery
```
package com.example.demo.surgery;

import org.springframework.stereotype.Component;

import com.example.demo.memory.Memory;
import com.example.demo.person.Person;

@Component
public class EyesSurgery implements Surgery {

    private final Memory memory;

    public EyesSurgery(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void plasticSurgery(Person person) {
        person.setEyes("유쌍");
        memory.save("눈");
    }

    @Override
    public void find() {
        for (String i : memory.getMemory()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
```
### class NoseSurgery
```
package com.example.demo.surgery;

import com.example.demo.memory.Memory;
import com.example.demo.person.Person;

public class NoseSurgery implements Surgery {

    private final Memory memory;

    public NoseSurgery(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void plasticSurgery(Person person) {
        person.setnose("직선 코");
        memory.save("코");
    }

    @Override
    public void find() {
        for (String i : memory.getMemory()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
```

---

## 2. 의존관계 자동 주입

위 코드에서 **"@Autowired"** 는 위에서 언급한 것처럼 의존관계를 자동으로 주입해주는 기능을 가진다.

이러한 의존관계 자동 주입방식은 크게 4가지 방법이 있다.
- 생성자 주입
- 수정자 주입(setter)
- 필드 주입
- 일반 메서드 주입

### 생성자 주입
위 코드의 한 방법이 바로 생성자 주입 방법이다.
말 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
생성자 호출 시점에 딱 1번만 호출된다.

'''
생성자가 1개만 있으면 **"@Autowired"** 를 생략할 수 있다.
'''

### 수정자 주입
set메서드에 **"@Autowired"** 를 붙이는 방법이다. 선택, 변경 가능성이 있는 의존관계에서 사용된다.

### 필드 주입
```
@Autowired
private final Memory memory;
```
바로 위와 같은 방식으로 필드에 바로 주입하는 방법이다.
코드가 간결하지만 외부에서 변경이 불가능하여 테스트하기가 힘들다.

### 일반 메서드 주입
말 그대로 일반 메서드를 통해 주입 받을 수 있는 방법이다.

이러한 여러 방법이 있지만 생성자 주입을 사용하도록 하자.
대부분의 의존관계 주입은 한번 일어나면 종료시점까지 의존관계를 변경할 일이 없고, 변해서는 안된다.
그리고 누군가 실수로 변경하는 일이 있을 수 있고, 애초에 변경하면 안되는 메서드가 열려있다는 것은 좋은 설계 방식이 아니다.

따라서 딱 한번만 호출되는 생성자 주입 방식을 사용하자.

---

## 3. 테스트
### class BeanTest
```
package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.surgery.Surgery;

public class BeanTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void basicScan() {
        Surgery surgery = ac.getBean(Surgery.class);
        Assertions.assertThat(surgery).isInstanceOf(Surgery.class);
    }
}
```
AutoAppConfig 클래스 파일에 자동으로 빈 등록이 잘 되었는지 테스트하였다.
```
AnnotationConfigApplicationContext(AutoAppConfig.class)
```
위 코드처럼 괄호 안에 AutoAppConfig를 넣어준다.

테스트를 돌려보면 잘 작동함을 알 수 있다.