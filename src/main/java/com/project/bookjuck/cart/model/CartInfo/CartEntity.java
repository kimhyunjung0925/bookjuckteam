package com.project.bookjuck.cart.model.CartInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartEntity {
    private int itemId;
    private int iuser;
    private int itemQty;

    private String title;
    private int priceStandard;
    public String getFormattedPrice() {
        return String.format("%,d", priceStandard);
    }

    public String getFormattedTotalPrice() {
        return String.format("%,d", priceStandard * itemQty);
    }

    public boolean booleanState;

}