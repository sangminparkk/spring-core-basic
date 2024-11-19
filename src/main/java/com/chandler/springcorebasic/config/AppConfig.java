package com.chandler.springcorebasic.config;

import com.chandler.springcorebasic.member.MemberService;
import com.chandler.springcorebasic.member.MemberServiceImpl;
import com.chandler.springcorebasic.member.MemoryMemberRepository;
import com.chandler.springcorebasic.order.FixDiscountPolicy;
import com.chandler.springcorebasic.order.OrderService;
import com.chandler.springcorebasic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}

