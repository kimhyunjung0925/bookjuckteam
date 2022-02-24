package com.project.bookjuck.book.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiSearchDto {
    private String type;
    private int startIdx = 1;
    private int maxResult = 10;
    private String searchWord;
    private String isbn;
    private String SearchTarget = "Book";

}
