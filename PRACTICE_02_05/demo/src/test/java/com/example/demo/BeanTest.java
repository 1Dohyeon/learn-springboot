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
