package com.chandler.springcorebasic.order;

import com.chandler.springcorebasic.member.Member;
import com.chandler.springcorebasic.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(member.getId(), itemName, itemPrice, discountPrice);
    }

}
