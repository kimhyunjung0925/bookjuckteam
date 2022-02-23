package com.project.bookjuck.book.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    //리스트에 뿌리는 책들 엔티티
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
}
