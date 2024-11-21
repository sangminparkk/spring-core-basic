package com.chandler.springcorebasic.singleton;

import com.chandler.springcorebasic.config.AppConfig;
import com.chandler.springcorebasic.member.MemberRepository;
import com.chandler.springcorebasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 조회시, 인스턴스가 매번 새로 생성됩니다")
    void printContainer() {
        AppConfig appConfig = new AppConfig();
        MemberRepository memberRepository1 = appConfig.memberRepository();
        MemberRepository memberRepository2 = appConfig.memberRepository();

        assertNotSame(memberRepository1, memberRepository2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonPattern() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너 싱글톤")
    void springContainer_singleton() {
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertSame(memberService1, memberService2);
    }

}

