package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.config.AppConfig;
import com.chandler.springcorebasic.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.chandler.springcorebasic.member.Grade.VIP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceImplTest {

    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("주문 생성")
    public void createOrder() throws Exception {
        // given
        Member memberA = new Member(1L, "memberA", VIP);
        memberService.join(memberA);

        // when
        Order createdOrder = orderService.createOrder(memberA.getId(), "itemA", 20000);

        // then
        assertEquals(1L, createdOrder.getMemberId());
        assertEquals("itemA", createdOrder.getItemName());
        assertEquals(1000, createdOrder.getDiscountPrice());
        assertEquals(19000, createdOrder.calculatePrice());
    }
}