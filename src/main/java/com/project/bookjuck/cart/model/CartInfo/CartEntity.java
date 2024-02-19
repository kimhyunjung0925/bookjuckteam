package com.project.bookjuck.cart.model.CartInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartEntity {
    private int itemId;
    private int iuser;
    private int itemQty;
    private String isbn;
    private String title;
    private int priceStandard;
    private String cover;

    public String getFormattedPrice() {
        return String.format("%,d", priceStandard);
    }

    public int getTotalPrice() {
        return priceStandard * itemQty;
    }
    public String getFormattedTotalPrice() {
        return String.format("%,d", priceStandard * itemQty);
    }

//    public boolean booleanState;


}
