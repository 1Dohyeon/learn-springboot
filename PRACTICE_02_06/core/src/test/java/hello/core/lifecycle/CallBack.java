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