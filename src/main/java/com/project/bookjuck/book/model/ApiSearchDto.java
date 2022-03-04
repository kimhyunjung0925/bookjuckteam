package com.project.bookjuck.book.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiSearchDto {
    private String type;//베스트인지 신간인지 구분하려고 넣은 변수
    private int startIdx = 1; //시작 페이지
    private int maxResult = 20; //한 페이지에 불러오는 갯수
    private String searchWord; //검색어
    private String isbn;
    private String SearchTarget = "Book"; //도서인지 외국도서인지 구분하기 위한 변수 (베스트, 신간, 리스트에 사용)
    private String SearchTarget2 = "All"; //우선 전체 검색(써치때 사용)
    private String category; //html에 링크주기 위한 변수
    private String selectVal; //최신순,인기순,가격순 결정한 값

}
