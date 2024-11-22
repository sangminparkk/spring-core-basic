package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.Member;
import org.springframework.stereotype.Component;

import static com.chandler.springcorebasic.member.Grade.VIP;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //초기 설정

    @Override
    public int discount(Member member, int price) { //TODO: price 필요한 이유 체크
        if (member.getGrade() == VIP) { //enum ==
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
