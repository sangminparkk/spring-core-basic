package com.chandler.springcorebasic.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {

    private Long memberId;//필요함
    private String itemName;
    private int itemPrice;
    private int discountPrice;//필요함

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() { //TODO: 계산된 결과값까지
        return this.itemPrice - this.discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
