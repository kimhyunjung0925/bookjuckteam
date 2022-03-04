package com.project.bookjuck.book.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDto {
    private String title;
    private String author;
    private String pubDate;
    private String description;
    private String isbn;
    private String isbn13;
    private int itemId;
    private int priceStandard;
    private String cover;
    private int categoryId;
    private String publisher;
    private boolean adult;
    private int customerReviewRank;
    private int bestRank;
    private String categoryName;


    private String selectVal = "";

    private String categoryBookjuck;

//    @Override
//    public int compareTo(BookDto dto) {
//        return this.pubDate.compareTo(dto.pubDate);
//    }

}
