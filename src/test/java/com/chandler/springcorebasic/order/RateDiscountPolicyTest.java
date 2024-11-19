package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.chandler.springcorebasic.member.Grade.BASIC;
import static com.chandler.springcorebasic.member.Grade.VIP;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    private RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용")
    public void vip_discount() throws Exception {
        // given
        Member member = new Member(1L, "memberA", VIP);

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertEquals(1000, discount);
    }

    // 항상 실패 테스트도 함께 검토할것
    @Test
    @DisplayName("VIP가 아니면 10% 할인 미적용")
    public void not_vip_no_discount() throws Exception {
        // given
        Member member = new Member(1L, "memberA", BASIC);

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertEquals(0, discount);
    }


}