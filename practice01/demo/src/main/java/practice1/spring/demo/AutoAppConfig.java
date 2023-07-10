package practice1.spring.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 패키지 위치를 지정하지 않는다면 default값 위치부터 찾음. default값은 이 클래스의 패키지이다.
        basePackages = "practice1.spring.demo", // 이 폴더 위치부터 하위파일까지만 찾음
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
