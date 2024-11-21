package com.chandler.springcorebasic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StatefulServiceTest {

    @Test
    @DisplayName("스프링 컨테이너에서 state하게 설계시 장애 발생")
    void statefulServiceSingleton() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestsConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 1만원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B사용자 2만원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자 주문 금액 조회
        System.out.println(statefulService1.getPrice());
        assertNotEquals(statefulService1.getPrice(), 10000);
    }

    static class TestsConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}