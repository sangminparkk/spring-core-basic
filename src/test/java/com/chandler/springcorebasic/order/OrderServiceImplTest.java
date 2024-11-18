package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.chandler.springcorebasic.member.Grade.VIP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceImplTest {

    private final OrderService orderService = new OrderServiceImpl();
    private final MemberRepository repository = new MemoryMemberRepository();

    @Test
    @DisplayName("주문 생성")
    public void createOrder() throws Exception {
        // given
        Member memberA = new Member(1L, "memberA", VIP);
        repository.save(memberA);

        // when
        Order createdOrder = orderService.createOrder(memberA.getId(), "itemA", 20000);

        // then
        assertEquals(1L, createdOrder.getMemberId());
        assertEquals("itemA", createdOrder.getItemName());
        assertEquals(1000, createdOrder.getDiscountPrice());
        assertEquals(19000, createdOrder.calculatePrice());
    }
}