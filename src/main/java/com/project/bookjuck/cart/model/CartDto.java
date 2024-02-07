package com.project.bookjuck.cart.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDto {
    private int itemId;
    private int iuser;
    private int itemQty;

    private String title;
    private int priceStandard;
    public String getFormattedPrice() {
        return String.format("%,d", priceStandard);
    }


}
