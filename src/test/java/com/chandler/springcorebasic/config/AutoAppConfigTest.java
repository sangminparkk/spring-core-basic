package com.chandler.springcorebasic.config;

import com.chandler.springcorebasic.AutoAppConfig;
import com.chandler.springcorebasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔 사용시 자동으로 빈 등록해준다")
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertInstanceOf(MemberService.class, memberService);
    }

}