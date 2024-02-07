package com.project.bookjuck.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

// 삭제할 때 필요한 정보만
@Getter
@Setter
@ToString
public class CartDeleteDto {
    private List<Integer> itemQty;
}

