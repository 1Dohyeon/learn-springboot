package com.example.demo.memory;

import java.util.ArrayList;

public interface Memory {
    // 수술 부위와 횟수를 저장하는 메서드
    void save(String kind);

    // 부위에 따른 횟수를 조회하는 메서드
    ArrayList<String> getMemory();
}
