package com.chandler.springcorebasic.config;

import com.chandler.springcorebasic.member.MemberRepository;
import com.chandler.springcorebasic.member.MemberService;
import com.chandler.springcorebasic.member.MemberServiceImpl;
import com.chandler.springcorebasic.member.MemoryMemberRepository;
import com.chandler.springcorebasic.order.DiscountPolicy;
import com.chandler.springcorebasic.order.FixDiscountPolicy;
import com.chandler.springcorebasic.order.OrderService;
import com.chandler.springcorebasic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}

