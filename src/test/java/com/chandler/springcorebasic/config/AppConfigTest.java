package com.chandler.springcorebasic.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_APPLICATION;

class AppConfigTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object bean = ac.getBean(beanName); // 타입을 지정 안하고 꺼내니까 object(Type)
            System.out.println(beanName + " = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) ac).getBeanDefinition(beanName);

            if (beanDefinition.getRole() == ROLE_APPLICATION) {
                Object bean = ac.getBean(beanName);
                System.out.println(beanName + " = " + bean);
            }
        }
    }

}