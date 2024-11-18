package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price); //넘기는 매개변수 체크
}
