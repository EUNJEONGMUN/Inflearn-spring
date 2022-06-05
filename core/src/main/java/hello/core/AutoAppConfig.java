package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 탐색할 패키지의 시작 위치 지정
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
) // @Component 가 붙은 클래스를 찾아 자동으로 빈으로 등록해줌
public class AutoAppConfig {
}
