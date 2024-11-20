package com.chandler.springcorebasic.singleton;

import com.chandler.springcorebasic.config.AppConfig;
import com.chandler.springcorebasic.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

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

}

