## #1
### 1. 회원 정보를 저장하고 확인할 수 있는 기능 구현.
### 2. 회원만 가질 수 있는 카드를 구현. 카드는 회원당 1장만 가질 수 있음.
### 3. 회원이 카드에 돈을 넣으면, 넣은 돈의 1%를 추가해서 카드에 포인트를 담을 수 있음.
### 4. 회원은 자신의 카드를 조회할 수 있음.

------

## #2
### 1. AppConfig를 통해서 인터페이스에 클래스 기능을 따로 부여하여 인터페이스에만 의존할 수 있도록 하기.
### 2. 생성자 주입.

------

## #3
### 1. AppConfig 스프링 기반으로 변경
### 2. 스프링 컨테이너 적용

------

## #4
### 1. 스프링 빈 등록(스프링 빈 이름)
### 2. 스프링 빈 불러오기

------

## #5
### 1. 싱글톤 패턴
### 2. 싱글톤 컨테이너
### 3. 싱글톤 방식의 주의점

------

## #6
### 1. 컴포넌트 스캔
### 2. 의존관계 자동 주입
### 3. 필터

------

## #7
### 1. 의존관계 주입: 생성자 주입 방식

의존관계 주입은 크게 4가지 방법이 있습니다.
- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입
- 일반 메서드 주입

우리가 지금까지 진행한 방법은 "생성자 주입 방식"입니다.
이 방법의 특징으로는 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다는 것입니다.
이 특징을 통해 불변, 필수 의존관계에서 사용될 수 있습니다.

"수정자 주입 방식"은 set 메소드를 통해서 필드값을 변경하여 의존관계를 주입하는 방법입니다.
그렇기에 선택, 변경 가능성이 있는 의존관계에서만 사용해야합니다.

필드 주입은 말 그대로 필드에서 바로 주입하는 방법입니다.
코드가 간결하지만, 외부에서 변경이 불가능하여 테스트하기가 매우 힘들다는 단점이 있습니다.

마지막으로 일반 메소드를 통해서 주입을 받을 수도 있습니다.
이를 "일반 메서드 주입"이라고 하며 일반적으로 잘 사용하지 않습니다.

과거에는 수정자 주입, 필드 주입을 이용했지만, 대부분이 생성자 주입을 권장합니다.
그 이유로는 의존관계 주입은 종료시점까지 변경할 일이 없으며, 변경되어서는 안됩니다.
변경을 할 수 있다면 누군가 실수로 변경할 수도 있으며, 애초에 변경하면 안되는 메소드가 열려있는 것은 좋은 설계 방법이 아닙니다.

### 2. 옵션처리

어떠한 메소드가 있을 때, 주입할 스프링 빈 없이 동작해야될 경우가 있습니다.
이때 @Autowired만 사용하면 주입 대상이 없을 경우 오류가 발생합니다. 
아래 방법을 이용하여 자동 주입 대상을 옵션으로 처리할 수 있습니다.
- @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
- org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
- Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.

### 3. 롬복

생성자 주입을 필드 주입처럼 간단하게 만들 수 있게 하는 라이브러리가 롬복입니다.
라이브러리를 적용하고 @RequiredArgsConstructor 를 이용하여 코드를 깔끔하게 사용할 수 있습니다.

------

## #8.
### 1. 조회 빈이 2개 이상일 경우: @Qualifier, @Primary

만약 CardService 의 하위 타입이 CardServiceImpl말고 하나가 더 있다면, 타입으로 빈을 조회할 때 문제가 발생한다.
이때 하위 타입으로 지정할 수 도 있지만, 하위 타입으로 지정하는 것은 DIP를 위배하고 유연성이 떨어집니다.
이때 사용할 수 있는 방법이 @Qualifier 와 @Primary 입니다.

하위 타입 클래스를 빈으로 등록할 때 @Qualifier("name") 을 붙여줍니다.

이제 생성자로 가서 CardService 타입 앞에 @Qualifier("name") 을 붙여주면 타입으로 빈을 조회할 때 @Qualifier 가 붙은 클래스를 우선 조회합니다.

@Primary는 하위 타입 클래스를 빈으로 등록할 때 같이 작성만 해주면, 타입으로 빈을 조회할 때, 우선 조회하게 됩니다.

@Qualifier와 @Primary 가 충돌할 경우 @Qualifier가 우선 조회됩니다.

### 2. 애노테이션
@Qualifier의 문제점으로는 컴파일시에 타입 체크가 안된다는 점입니다.
이럴 때 애노테이션을 만들어서 문제를 해결할 수 있습니다.
애노테이션을 작성하고 나서는 @Qualifier("name")이 아닌 @Name으로 재정의시킬 수 있습니다.

### 3. 조회한 빈이 모두 필요한 경우
예를 들어 두개의 클래스로 구체화된 인터페이스에서 클라이언트가 클래스를 선택할 수 있다고 가정한다면, 해당 타입의 스프링 빈이 다 필요할 경우가 생길 수도 있다.
이런 경우 스프링을 사용하여 전략 패턴을 매우 간단하게 구현할 수 있다.

아래 예제는 Map과 List에 스프링 빈을 담는 코드 예제이다.
```
static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap,
                List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }
```

1. 로직 분석
    DiscountService는 Map으로 모든 DiscountPolicy 를 주입받는다. 
    이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다.
    discount() 메서드는 discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 스프링 빈을 찾아서 실행한다. 
    물론 “rateDiscountPolicy”가 넘어오면 rateDiscountPolicy 스프링 빈을 찾아서 실행한다.

2. 주입 분석
    Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로
    DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
    List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
    만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다

```
new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
```

위 예제 코드처럼 스프링 빈을 선택하면서 AutoAppConfig.class , DiscountService.class 를 파라미터로 넘겨 해당 클래스를 자동으로 스
프링 빈으로 등록 할 수 있다.

**
편리한 자동 기능을 기본으로 사용하자!
직접 등록하는 기술 지원 객체는 수동 등록, 다형성을 적극 활용하는 비즈니스 로직은 수동 등록을 고민해보자!
스프링 부트가 아니라 내가 직접 기술 지원 객체를 스프링 빈으로 등록한다면 수동으로 등록해서 명확하게 드러내는 것이 좋다.
**