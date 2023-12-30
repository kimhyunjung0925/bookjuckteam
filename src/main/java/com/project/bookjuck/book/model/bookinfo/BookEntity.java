package com.project.bookjuck.book.model.bookinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookEntity {
    private String title;
    private String author;
    private String pubDate;
    private String description;
    private String isbn;
    private String isbn13;
    private int itemId;
    private int priceStandard;
    public String getFormattedPrice() {
        return String.format("%,d", priceStandard);
    }
    private String cover;
    private int categoryId;
    private String publisher;
    private boolean adult;
    private int customerReviewRank;
    private int bestRank;
    private String fullDescription;
    private String fullDescription2;
    private BookSubInfoEntity subInfo;
    private String categoryName;
}
