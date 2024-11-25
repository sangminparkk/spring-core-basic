package com.chandler.springcorebasic.config;

import com.chandler.springcorebasic.member.MemberRepository;
import com.chandler.springcorebasic.member.MemberService;
import com.chandler.springcorebasic.member.MemberServiceImpl;
import com.chandler.springcorebasic.member.MemoryMemberRepository;
import com.chandler.springcorebasic.order.DiscountPolicy;
import com.chandler.springcorebasic.order.FixDiscountPolicy;
import com.chandler.springcorebasic.order.OrderService;
import com.chandler.springcorebasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Primary
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

