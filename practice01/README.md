# Spring Practice, Day 1

- 회원 비즈니스 설계

- 요구사항:
1. 회원을 가입하고 조회할 수 있다.
2. 회원은 일반과 VIP 두 가지 등급이 있다.
3. 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
## demo > src > main > java > practice1 > spring > demo > Member
### Grade enum
요구사항을 보면 회원은 일반과 VIP 두 가지 등급으로 나뉩니다.

이때 클래스로 생성하지 말고 열겨형(enumuration)인 "enum"으로 생성하여, 한정된 값들의 집합으로 구현할 수 있습니다.
코드 읽기가 상대적으로 더 쉽고, 유지 관리하기가 쉽다는 장점이 있습니다.

### Member class
회원 class입니다.
회원은 고유 id를 가지고, 회원의 이름 그리고 앞에서 생성한 grade 정보를 가집니다.
또한 이런 정보를 return하는 get메소드, 그리고 값을 설정하는 set메소드까지 생성합니다.

### MemberRepository interface
member 정보를 저장하는 save 메소드를 만들고, MemberId를 매개변수로 받아 해당 ID를 가진 member를 조회하는 메소드 findBy를 생성합니다.

문제 요구사항에서 데이터에 대해서 아직 미확정이라고 하였기 때문에, 인터페이스로 구현체를 언제든지 갈아끼울 수 있도록 설계합니다.

인터페이스는 어떤 동작을 수행하는지만 정의하고, 실제 동작은 해당 인터페이스를 구현하는 클래스에서 구현하는 것을 의미합니다.
그렇기에 인터페이스는 주로 코드의 재사용성과 유연성을 높이기 위해서 사용합니다.

인터페이스의 특징으로는 다향성(polymorphism)의 한 가지 형태로도 사용될 수 있습니다.
예를 들어, 인터페이스 타입으로 변수를 선언하고 구현된 객체를 대입하여 해당 인터페이스의 메소드 호출할 수 있습니다.

또한 인터페이스는 다중 상속이 가능하며, 클래스가 여러 개의 인터페이스를 동시에 구현할 수 있습니다. 
이를 통해 다양한 인터페이스의 기능을 한 클래스에서 동시에 사용할 수 있습니다.

### MemoryMemberRepository class
MemberRepository를 implements합니다.
데이터를 메모리에 저장할 HashMap store를 생성하고, MemberRepository interface의 메소드 save와 findeById메소드에 기능을 부여합니다.

save메서드는 member를 파라미터로 받고 Member class의 getId메소드를 이용해서 member의 id와 member를 store에 담습니다.
findById는 HashMap의 get메소드를 통해서 memberId를 파라미터로 받고 그에 맞는 value값인 member를 return합니다.

### MemberService interface
interface로 join과 findMember함수를 생성합니다.

### MemberServiceImpl class
MemberService interface를 implements합니다.
interface의 함수들 join과 findMember에 기능을 부여합니다.

우선 그 전에 MemoryMemberRepository를 구현한 객체를 MemberRepository 타입의 memberRepository 필드에 할당합니다.

join 함수는 사용자로부터 member를 파라미터로 받고 memberRepository.save를 통해 member를 저장합니다.
findMember는 사용자로부터 memberId를 파라미터로 받고, memberRepository.findById를 이용하여 member 자체를 return합니다.

위 함수를 보면 '사용자로부터' 파라미터를 받는 특징을 갖고 있음을 알 수 있습니다.
따라서 사용자가 직접 사용하게 되는 함수임을 알 수 있습니다.

## demo > src > main > java > practice1 > spring > demo
### MemberApp class
위에서 만든 회원 저장과 탐색 기능을 테스트 하는 class입니다.
우선 MemberServiceImpl 구현한 객체를 MemberService 타입의 memberService 필드에 할당합니다.

Id: 1L, name: "MemberA", Grade는 VIP인 고객 member의 정보를 memberService필드의 join 함수를 통해 메모리에 저장합니다.

findeMember는 memberService필드의 findMember 함수를 이용하여 사용자로부터 id를 받고 return받은 member 정보를 지니게 됩니다.

member를 그대로 출력할 수 없으니 Member class의 getName함수를 이용해서 member와 findMember의 name이 동일한 인물인지 테스트합니다.

## demo > src > test > java > practice1 > spring > demo > Member
### MemberServiceTest
MemberServiceTest 클래스는 org.junit.jupiter.api.Test 애너테이션을 가지고 있습니다. 
이 애너테이션은 JUnit 프레임워크에서 테스트 메서드임을 나타냅니다.

MemberService 인터페이스를 구현한 MemberServiceImpl 클래스의 인스턴스인 memberService를 생성합니다. 
이는 테스트할 대상인 MemberService의 구현체입니다.

@Test 애너테이션이 적용된 join() 메서드가 정의되었습니다. 
이 메서드는 Member 객체를 생성하고, 해당 멤버를 memberService.join()을 호출하여 회원 가입 처리합니다.

Member 객체를 생성할 때, 1L이라는 아이디, "memberA"라는 이름, 그리고 Grade.VIP라는 등급을 가진 회원을 생성합니다. 
이는 테스트를 위한 가정(Given)입니다.

memberService.join(member)를 호출하여 회원을 가입시킵니다.
memberService.findMember(1L)을 호출하여 아이디가 1L인 회원을 조회합니다. 
이는 가입된 회원을 찾는 것을 검증하기 위한 테스트입니다.

Assertions.assertThat(member).isEqualTo(findMember)를 사용하여 member 객체와 findMember 객체를 비교합니다. 
이를 통해 회원 가입 후 회원을 정상적으로 찾았는지 검증합니다.

위의 코드는 테스트를 위한 단위 테스트 메서드인 join()을 정의하고 있습니다. 
이 메서드는 MemberService의 join()과 findMember() 메서드를 검증하여 회원 가입 및 조회 기능을 테스트하는 역할을 수행합니다.

# Spring Practice, Day 2

2일차에서는 주문과 할인 도메인을 설계하였습니다.

- 주문과 할인 정책
1. 회원은 상품을 주문할 수 있다.
2. 회원 등급에 따라 할인 정책을 적용할 수 있다.
3. 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
4. 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)

위 조건을 토대로 구현을 크게 4가지로 나누었습니다.
1. 주문생성
2. 회원 조회
3. 할인 적용
4. 주문 결과 반환

실제로는 주문 데이터를 DB에 저장해야겠지만, 주문 결과만을 리턴시키겠습니다.

## demo > src > main > java > practice1 > spring > demo > Discount
할인 여부를 파악하는 패키지입니다.
### interface DiscountPolicy
interface를 통해서 int 타입을 return하게 될 함수 discount를 구현합니다.
이 변수는 Member 타입과 int 타입을 파라미터로 받아 생성됩니다.

### class FixDiscountPolicy implements DiscountPolicy
class를 통해서 interface에서 만든 함수를 구체화합니다.

우선 Member 타입과 int 타입 변수들을 파라미터로 받습니다.
Member 타입의 파라미터 변수의 Grade가 VIP일 경우 1000원을 할인해야하므로 1000을 return하고, 아닐 경우 0을 return합니다.

최종적으로 int 타입의 discount는 Member가 VIP인지 아닌지에 따라 1000 OR 0 값을 return하게 됩니다.

## demo > src > main > java > practice1 > spring > demo > Order
클라이언트로부터 직접 주문을 받는 패키지입니다.

### class Order
주문 정보를 담게되는 클래스입니다.

회원 id (memberId), 구매하는 상품의 이름과 가격 (itemName, itemPrice), 할인 금액 변수 (discountPrice).
이 4개의 변수를 가지게 되는 클래스입니다.

각 변수들을 return 하는 함수 뿐만 아니라 최종 할인된 금액을 return 하는 함수 caculaterPrice()를 생성합니다.

또한 출력할 때 편하도록 toString()메소드를 생성합니다.
이 메소드는 Order 객체를 출력할 시 실행됩니다.

### interface OrderService
Order 타입의 함수 createOrder를 생성합니다. 
이는 memberId, itemName, itemPrice를 파라미터로 받습니다.

### class OrderServiceImpl implements OrderService
우선 day1에서 생성하였던 회원정보를 저장하기 위해서 MemoryMemberRepository를 구현한 객체를 MemberRepository 타입의 필드 memberRepository를 생성합니다.

그리고 FixDiscountPolicy를 구현한 객체를 DiscountPolicy 타입의 필드 discountPolicy를 생성합니다.

그다음 OrderService interface에서 생성한 createOrder함수를 구체화합니다.
memberId와 itemName, itemPrice를 파라미터로 받습니다.

파라미터로 받은 memberId를 통해서 memberRepository의 findById 함수를 통해 memberId를 가진 Member 타입의 데이터를 그대로 member 변수 안에 담습니다.

member와 파라미터로 받은 itemPrice를 통해서 discountPolicy의 discount 함수를 이용해서 1000원 OR 0원을 return 받으며, 그 값을 discoutnPrice 변수에 담습니다.

그리고 이 함수는 함수 createOrder 함수는 최종적으로 memberId, itemName, itemPrice, discountPrice를 파라미터로 받는 Order 객체를 생성하고 반환합니다.