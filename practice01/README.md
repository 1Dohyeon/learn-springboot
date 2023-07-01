# Spring Practice, Day 1

- 회원 비즈니스 설계

- 요구사항:
1. 회원을 가입하고 조회할 수 있다.
2. 회원은 일반과 VIP 두 가지 등급이 있다.
3. 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
## demo > src > main > java > practice1 > spring > demo > memeber
### Grade enum
요구사항을 보면 회원은 일반과 VIP 두 가지 등급으로 나뉩니다.
이때 클래스로 생성하지 말고 열겨형(enumuration)인 "enum"으로 생성하여, 한정된 값들의 집합으로 구현할 수 있습니다.
코드 읽기가 상대적으로 더 쉽고, 유지 관리하기가 쉽다는 장점이 있습니다.

### Member class
회원 class입니다.
회원은 고유 id를 가지고, 회원의 이름 그리고 앞에서 생성한 grade 정보를 가집니다.
또한 이런 정보를 return하는 get메소드, 그리고 값을 설정하는 set메소드까지 생성합니다.

### MemberRepository interface