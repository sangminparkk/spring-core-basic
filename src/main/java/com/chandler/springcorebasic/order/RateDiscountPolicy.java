package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.Member;

import static com.chandler.springcorebasic.member.Grade.VIP;

public class RateDiscountPolicy implements DiscountPolicy {

    private final double discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == VIP) {
            return (int) (price / discountPercent);
        } else {
            return 0;
        }
    }
}
