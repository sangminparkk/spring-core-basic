package com.chandler.springcorebasic.config;

import com.chandler.springcorebasic.member.MemberRepository;
import com.chandler.springcorebasic.member.MemberService;
import com.chandler.springcorebasic.member.MemberServiceImpl;
import com.chandler.springcorebasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    @DisplayName("빈 이름 조회하기")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @DisplayName("없는 빈 이름 조회하기")
    void findBeanByName_fail() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("itemService", MemberService.class));
    }

    @Test
    @DisplayName("빈 타입 조회하기")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 2개 이상 있으면, 중복 오류가 발생한다")
    void findBeanByType_Duplicate_fail() { // DuplicateBeanByType_fail ->
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 2개 이상 있으면, 이름으로 조회한다")
    void findBeanByName_Duplicate() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository1", MemberRepository.class);
        assertInstanceOf(MemoryMemberRepository.class, memberRepository);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findBeansByType() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        Map<String, MemberRepository> beans = applicationContext.getBeansOfType(MemberRepository.class);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + ", value = " + beans.get(key));
        }

        assertEquals(2, beans.size()); //TODO: collection은 size로 검증하기
    }

    @Test
    @DisplayName("빈 설정 메타정보 확인하기")
    void findBeanMetaInfo() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ((AnnotationConfigApplicationContext) ac).getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == ROLE_APPLICATION) {
                System.out.println("beanDefinitionName " + beanDefinitionName + " / " + "beanDefinition" + beanDefinition);
                System.out.println();
            }
        }
    }

    @Configuration
    static class SameBeanConfig { // static : 외부 클래스에서만 동작한다는 의미

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}