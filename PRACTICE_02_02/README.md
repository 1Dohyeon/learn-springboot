# 2.  [스프링 #2] AppConfig 리팩토링

저번 포스팅에서 간단하게 클래스와 인터페이스 그리고 객체지향에 대하여 공부하였다.
객체지향의 원칙 중 하나인 DIP 원칙을 위반하지 않기 위해 AppConfig 리팩터링을 언급하였다.

-----

## 1. AppConfig
**"AppConfig"** 란 구현 객체를 생성하고, 연결하는 책임을 가지는 클래스이다.

### class AppConfig
```
import surgery.NoseSurgery;
import surgery.Surgery;

public class AppConfig {
    public Surgery surgery() {
        // return new EyesSurgery();
        return new NoseSurgery();
    }
}
```
AppConfig 리팩터링을 통하여 구체 클래스를 대신 선택 후 연결을 할 수 있게 되었다.
AppConfig는 생성한 객체 인스턴스의 참조 레퍼런스를 생성자를 통해서 주입하였다.
이런 모습은 마치 의존관계를 외부에서 주입해주는 것처럼 보이기에 **"DI(Dependency Injection)"** 라고 부른다.

### class Main
```
import person.Person;
import surgery.Surgery;

public class Main {
    public static void main(String[] args) {
        Person 짱구 = new Person("유쌍", "직선 코", "큰입");
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        // AppConfig 타입 객체 생성
        AppConfig appConfig = new AppConfig();

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        Surgery surgery = appConfig.surgery();
        surgery.plasticSurgery(철수);
        System.out.println("철수 코 = " + 철수.getnose());
    }
}
```
AppConfig 덕분에 AppConfig 타입의 객체를 생성하고, 이 객체를 이용해서 Surgery 타입의 객체를 생성하게 되어 인터페이스를 구체화하는 클래스가 필요없게 되었다.

즉, 이제는 인터페이스에만 의존하므로 DIP 원칙을 위반하지 않는다.

수술 부위를 코가 아니라 눈으로 바꾸고 싶다면 아래와 같이 AppConfig 파일만 수정하면 된다.
### class AppConfig
```
import surgery.NoseSurgery;
import surgery.Surgery;

public class AppConfig {
    public Surgery surgery() {
        return new EyesSurgery();
        // return new NoseSurgery();
    }
}
```
-----

## 2. 클래스 추가

AppConfig의 예제를 위해서 클래스를 좀 더 추가해보았다.
### class Memory
```
package memory;

import java.util.ArrayList;

public interface Memory {
    // 수술 부위와 횟수를 저장하는 메서드
    void save(String kind);

    // 부위에 따른 횟수를 조회하는 메서드
    ArrayList<String> getMemory();
}
```
### class MemoryImpl
```
package memory;

import java.util.ArrayList;

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
위 메모리 예제 코드의 save 메서드는 ArrayList에 String 타입으로 수술 종류를 담는다.
그리고 getMemory 메서드는 저장 공간인 store을 반환하는 메서드이다.

### class AppConfig 일부
```
    public Memory memory() {
        return new MemoryImpl();
    }
```
AppConfig 클래스에서는 구체 클래스를 대신 선택할 수 있도록 memory() 메서드를 선언하였다.
### class Surgery
```
package surgery;

import person.Person;

public interface Surgery {
    void plasticSurgery(Person person);

    void find();
}
```
### class NoseSurgery
```
package surgery;

import memory.Memory;
import person.Person;

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
### class AppConfig 일부
```
    public Surgery surgery() {
        return new EyesSurgery(
                memory());
        /*
         * return new NoseSurgery(
         * memory());
         */
    }

    public Memory memory() {
        return new MemoryImpl();
    }
```
수술 클래스에서 메모리에 수술 종류를 담기 위해 Memory타입 생성하였다.
이때 구체화 클래스 없이 인터페이스에만 의존하여 객체를 생성한 것을 알 수 있다.

AppConfig에서 memory()를 생성자를 통해서 EyesSurgery와 NoseSurgery에 주입시켜준다.
NoseSurgery 클래스 코드를 보면 생성자가 있는 것을 알 수 있다.
AppConfig가 이 생성자를 통해서 memory()를 주입한다.

NoseSurgery 입장에서는 생성자를 통해서 어떤 구현 객체가 들어올지는 알 수 없다.
AppConfig(외부)에서 결정나기 때문이다.

그렇기에 NoseSurgery, EyesSurgery는 의존관계에 직접적인 영향을 받지 않게 된다.

plasticSurgery 메서드 안에서 수술 종류를 메모리에 추가한다.
그리고 새로운 메서드인 function은 Memory 타입 객체의 메서드에서 메모리를 반환하는 메서드를 이용하여 메모리에 있는 데이터를 출력한다.
마지막으로 EyesSurgery 클래스도 위에서 "코"를 "눈"으로 바꾸고 똑같이 만들어주었다.

이제 AppConfig 를 이용한 main(클라우드)에서 잘 작동하나 테스트를 해보았다.
### class Main
```
import person.Person;
import surgery.Surgery;

public class Main {
    public static void main(String[] args) {
        Person 철수 = new Person("무쌍", "독수리 코", "큰입");
        Person 맹구 = new Person("무쌍", "매부리 코", "작은");

        // AppConfig 타입 객체, Surgery 타입 객체 생성
        AppConfig appConfig = new AppConfig();
        Surgery surgery = appConfig.surgery();

        // 철수의 코 성형수술
        System.out.println("철수 코 = " + 철수.getnose());
        // surgery.plasticSurgery(철수);
        System.out.println("철수 코 = " + 철수.getnose());

        // 맹구의 코 성형수술
        System.out.println("맹구 코 = " + 맹구.getnose());
        // surgery.plasticSurgery(맹구);
        System.out.println("맹구 코 = " + 맹구.getnose());

        surgery.find();

        // 철수의 코 성형수술
        System.out.println("철수 눈 = " + 철수.getEyes());
        surgery.plasticSurgery(철수);
        System.out.println("철수 눈 = " + 철수.getEyes());

        // 맹구의 코 성형수술
        System.out.println("맹구 눈 = " + 맹구.getEyes());
        surgery.plasticSurgery(맹구);
        System.out.println("맹구 눈 = " + 맹구.getEyes());
    }
}
```
### result
```
철수 코 = 독수리 코
철수 코 = 독수리 코
맹구 코 = 매부리 코
맹구 코 = 매부리 코

철수 눈 = 무쌍
철수 눈 = 유쌍
맹구 눈 = 무쌍
맹구 눈 = 유쌍

```
코가 두번 출력 된거보니 잘 나왔다.

-----

요약하자면, 기존에는 어느 인스턴스에 의해 생성된 객체는 그 인스턴스를 구체화 하는 클래스에도 의존을 했었다.

하지만 AppConfig 리팩터링을 통하여 구체 클래스를 대신 선택하여 연결을 하였고, 인터페이스에 의해 생성된 객체는 인터페이스에만 의존하게 되었다.

NoseSurgery, EyesSurgery에 어떤 구현체가 들어갈지는 AppConfig 에서 결정되므로, 메소드 정책을 변경할 때 AppConfig 에서 다른 구현체를 연결하면 된다.

이러한 상황을 클라이언트인 NoseSurgery, EyesSurgery 입장에서 본다면 의존관계를 외부에서 주입하는 것처럼 보여서 DI(Dependency Injection), 의존관계 주입이라고 부른다.

-----

아직 공부 중이라 틀린 부분이 있을 수도 있습니다.
이 포스팅은 제가 공부한 것을 정리하기 위함이며 올바르지 않은 정보가 전달될 수도 있습니다.
혹시 오류가 있는 부분이 있다면 알려주시면 감사하겠습니다.

