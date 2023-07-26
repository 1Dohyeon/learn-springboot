# [스프링 #1] 자바: 클래스와 인터페이스 그리고 객체 지향

스프링을 공부하기 전에 자바의 클래스와 인터페이스 그리고 객체지향을 이해하기 위해서 자바를 다시 공부하였다.

## 1. class 
클래스(class)란 객체를 생성하기 위한 청사진 역할을 한다.
클래스는 데이터(속성)와 해당 데이터를 처리하기 위한 메서드(함수)를 포함한다.
객체는 클래스 기반으로 생성되며, 동일한 클래스로부터 생성된 객체들은 같은 속성과 동작을 가지게 된다.

비유를 들어 설명하자면, 사람이라는 클래스는 눈, 코, 입, 손, 발 등 여러 속성을 가지고 있고, 보기, 먹기, 숨쉬기, 걷기 등 여러 메서드를 가진다.
이름은 다르지만 각각 짱구, 철수, 맹구 라는 객체가 있고, 이 객체들이 사람 클래스를 기반으로 생성되었다면, 이 객체들은 동일한 속성과 메서드를 가지게 된다.

### class Person
```
public class Person {
    private String eyes; // 눈
    private String nose; // 코
    private String mouth; // 입

    public String getEyes() {
        return eyes;
    }

    public String getnose() {
        return nose;
    }

    public String getmouth() {
        return mouth;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public void setnose(String nose) {
        this.nose = nose;
    }

    public void setmouth(String mouth) {
        this.mouth = mouth;
    }

    // 생성자
    public Person(String eyes, String nose, String mouth) {
        this.eyes = eyes;
        this.nose = nose;
        this.mouth = mouth;
    }
}

```
위 예제처럼 Person이라는 클래스는 눈 코 입 속성을 가지며, 각각의 눈, 코, 입 정보를 반환하는 메서드와 눈, 코, 입 정보를 재설정할 수 있는 메서드를 가지고 있다.

### class Main 
```
public class Main {
    public static void main(String[] args) {
        Person 짱구 = new Person("유쌍", "직선 코", "큰입");
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        System.out.println("짱구 코 = " + 짱구.getnose());
        System.out.println("철수 코 = " + 철수.getnose());
        System.out.println("맹구 코 = " + 맹구.getnose());
    }
}
```
### result
```
짱구 코 = 직선 코
철수 코 = 독수리 코
맹구 코 = 매부리 코
```
위 코드 예제를 통해 각각의 다른 이름을 가진 짱구, 철수, 맹구가 Person이라는 클래스를 통해 생성된 객체임을 알 수 있다.

같은 클래스를 이용하여 객체를 생성했으므로, 클래스 내에 있는 getnose() 메서드를 모두 이용할 수 있는 것이다.

이렇게 각 기능마다 클래스를 나누어 필요할 때만 호출하는 방식을 **"객체지향"** 이라고 부른다.

---

## 2. interface

인터페이스란 메서드들의 집합으로, 구체적인 내용을 포함하지 않고 메서드의 프로토타입만을 정의한다.

즉 메서드 이름만 정의하며 추상적인 특징을 가진다.
그렇기에 클래스를 통해서 인터페이스에 선언된 메서드들을 반드시 구현해야한다.

### interface Surgery
```
public interface Surgery {
    void plasticSurgery(Person person);
}
```
위 설명처럼 코드를 보면 plasticSurgery라는 성형수술 메서드가 있는거는 알겠는데, 도대체 어디 부위를 수술하는 것인지 자세하게 설명되어있지 않고, 추상적으로 메서드가 표현되어 있다.

### class NoseSurgery implements Surgery
```
public class NoseSurgery implements Surgery {

    @Override
    public void plasticSurgery(Person person) {
        person.setnose("직선 코");
    }
}
```
클래스를 통해 인터페이스에서 추상적으로 작성한 메서드를 구체화 시켜주었다.

Surgery 인터페이스를 구현하는 클래스이므로 클래스 옆에 **"implements Surgery"**를 작성해주었고, **"@Override"** 를 작성하여 인터페이스 메서드를 구체화 함을 표시하였다.

### class Main
```
public class Main {
    public static void main(String[] args) {
        Person 짱구 = new Person("유쌍", "직선 코", "큰입");
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        System.out.println("짱구 코 = " + 짱구.getnose());
        System.out.println("철수 코 = " + 철수.getnose());
        System.out.println("맹구 코 = " + 맹구.getnose());

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        Surgery surgery = new NoseSurgery();
        surgery.plasticSurgery(철수);

        System.out.println("철수 코 = " + 철수.getnose());
    }
}
```
### result
```
짱구 코 = 직선 코
철수 코 = 독수리 코
맹구 코 = 매부리 코
철수 코 = 독수리 코
철수 코 = 직선 코
```
Surgery 클래스를 이용하여 객체 surgery를 생성하였다.
객체 surgery에 있는 메서드를 이용하여 철수의 코를 직선 코로 바꾸었다.

> **그렇다면 인터페이스를 클래스로 구체화 시키는 것이 뭐가 좋은 것일까?**
인터페이스를 사용하는 이유로는 객체지향 프로그래밍에서 다양한 이점을 제공하기 때문이다.

예를 들어서 Surgery interface를 이용하여 객체에 기능을 갑자기 변경하고 싶을 때, 미리 따로 구현해 놓은 클래스로 바꾸어 적용하여 더 편하게 코드를 고칠 수 있게 된다.

### class EyesSurgery implements Surgery
```
public class EyesSurgery implements Surgery {

    @Override
    public void plasticSurgery(Person person) {
        person.setEyes("유쌍");
    }
}
```
위 예제 코드는 Surgery 인터페이스를 구체화 한 클래스이다.
기존 구체화 클래스와는 달리 유쌍으로 변환시키는 눈 수술 역할을 담당한다.

### class Main
```
public class Main {
    public static void main(String[] args) {
        Person 짱구 = new Person("유쌍", "직선 코", "큰입");
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        System.out.println("짱구 코 = " + 짱구.getnose());
        System.out.println("철수 코 = " + 철수.getnose());
        System.out.println("맹구 코 = " + 맹구.getnose());

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        Surgery surgery1 = new NoseSurgery();
        surgery1.plasticSurgery(철수);

        System.out.println("철수 코 = " + 철수.getnose());

        // 맹구의 눈 성형수술
        System.out.println("맹구 눈 = " + 맹구.getEyes());
        Surgery surgery2 = new EyesSurgery();
        surgery2.plasticSurgery(맹구);

        System.out.println("맹구 눈 = " + 맹구.getEyes());
    }
}
```
### result
```
짱구 코 = 직선 코
철수 코 = 독수리 코
맹구 코 = 매부리 코
철수 코 = 독수리 코
철수 코 = 직선 코
맹구 눈 = 무쌍
맹구 눈 = 유쌍
```

위 Main 클래스에서 알 수 있듯이 똑같이 Surgery 인터페이스를 통해서 생성한 객체인데 뒷부분만 변경해주었다고 코 수술을 눈 수술로 변경할 수 있게 되었다.

여러가지 장점들이 더 있지만, 이러한 이유들 때문에 인터페이스를 사용하는 것이다.

---

## 3. 객체 지향
위 코드들을 보면 인터페이스와 클래스를 잘 나누어 객체 지향을 잘 구현한 것처럼 보인다.

하지만 객체 지향을 적절하게 만족시키기 위해서는 **SRP** 를 만족시켜야하며, **DIP**를 위반해서는 안된다.

**"SRP"** 란 단일 책임 원칙이라고 부르며, 클래스나 모듈은 하나의 책임만 가져야 한다는 원칙이다. 한 클래스가 여러 개의 책임을 가지면 코드가 복잡해지고, 변경 시 다른 책임에도 영향을 미칠 수 있으므로 각 기능과 책임을 분리하여 모듈을 단순하고 응집력 있게 만든다.

**"DIP"** 란 의존성 역전 원칙이라 부르며, 이는 상위 수준의 모듈은 하위 수준의 모듈에 의존해서는 안되며, 모두 추상화된 인터페이스에 의존해야 한다는 원칙이다. 즉, 구체적인 구현이 아닌 인터페이스나 추상 클래스에 의존함으로써 모듈 간의 결합도를 줄여야 한다. 이렇게 하면 변경이 발생할 때 영향 범위가 줄어들어 유지보수가 용이해진다.

### class Main 일부
```
	Surgery surgery1 = new NoseSurgery();
    Surgery surgery2 = new EyesSurgery();
```
위 코드 예제를 보면 surgery1, surgery2 라는 객체들은 Surgery 인터페이스에 의존하면서 인터페이스를 구체화한 각각의 클래스인 NoseSurgery와 EyesSurgery 클래스에도 의존함을 알 수 있다.

즉, SRP 원칙은 지켜졌지만 DIP 원칙을 위반한 것이다.

> **그렇다면 어떻게 하면 DIP 원칙을 위반하지 않고, 인터페이스에만 의존하도록 할 수 있을까?**
AppConfig 리팩터링을 한다면 인터페이스에만 의존할 수 있게 코드를 짤 수 있다.

---

AppConfig 리팩터링에 대해서는 다음 포스팅에서 다루도록 하겠습니다.

아직 공부 중이라 틀린점이 있을 수도 있습니다.
이 포스팅은 제가 공부한 것을 정리하기 위함이며 올바르지 않은 정보가 전달될 수도 있습니다.
혹시 오류가 있는 부분이 있다면 알려주시면 감사하겠습니다.
