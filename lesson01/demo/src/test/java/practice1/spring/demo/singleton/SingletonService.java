package practice1.spring.demo.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    // static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
    // 이 메소드를 통해서만 인스턴스를 조회할 수 있고, 이 메소드를 호출하면 항상 같은 인스턴스를 반환한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // private으로 외부에서 new 키워드로 객체 인스턴스 생성을 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
