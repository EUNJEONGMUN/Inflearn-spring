package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기") // 스프링에 등록된 모든 빈 출력
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 정의된 이름 등록
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // bean 꺼내기
            System.out.println("name = " + beanDefinitionName + " object = " + bean); // key, value
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 정의된 이름 등록
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { // 내가 등록한 빈들 + 외부 라이브러리
                Object bean = ac.getBean(beanDefinitionName); // bean 꺼내기
                System.out.println("name = " + beanDefinitionName + " object = " + bean); // key, value
            }
        }
    }
}
