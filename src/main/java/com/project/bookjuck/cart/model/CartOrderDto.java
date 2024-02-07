package com.project.bookjuck.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

// 주문할 때 필요한 정보만
@Getter
@Setter
@ToString
public class CartOrderDto {
    public static class Param {
        private Integer itemID;
        private Integer itemQty;
    }
    private List<Param> list;
}

