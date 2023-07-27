package com.example.demo;

import com.example.demo.person.Person;
import com.example.demo.surgery.Surgery;

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

        // 철수의 코 성형수술
        System.out.println("철수 눈 = " + 철수.getEyes());
        surgery.plasticSurgery(철수);
        System.out.println("철수 눈 = " + 철수.getEyes());

        // 맹구의 코 성형수술
        System.out.println("맹구 눈 = " + 맹구.getEyes());
        surgery.plasticSurgery(맹구);
        System.out.println("맹구 눈 = " + 맹구.getEyes());

        surgery.find();
    }
}
