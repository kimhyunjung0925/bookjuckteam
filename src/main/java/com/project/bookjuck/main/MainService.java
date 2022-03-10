package com.project.bookjuck.main;

import com.project.bookjuck.book.BookService;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    private BookService bookService;

    public List<BookDto> domesticbook (){
        ApiSearchDto searchDto = new ApiSearchDto();
        searchDto.setType("Bestseller");
        searchDto.setSearchTarget("Book");
        searchDto.setMaxResult(7);
        return bookService.getData(searchDto,"https://www.aladin.co.kr/ttb/api/ItemList.aspx");
    }

    public List<BookDto> foreignbook (){
        ApiSearchDto searchDto = new ApiSearchDto();
        searchDto.setType("Bestseller");
        searchDto.setSearchTarget("Foreign");
        searchDto.setMaxResult(4);
        return bookService.getData(searchDto,"https://www.aladin.co.kr/ttb/api/ItemList.aspx");
    }

    public List<BookDto> steadybook (){ //스테디가 아니지만 스테디인척 할 예정
        ApiSearchDto searchDto = new ApiSearchDto();
        searchDto.setType("BlogBest");
        searchDto.setSearchTarget("Book");
        searchDto.setMaxResult(4);
        return bookService.getData(searchDto,"https://www.aladin.co.kr/ttb/api/ItemList.aspx");
    }

    public List<BookDto> newbook (){
        ApiSearchDto searchDto = new ApiSearchDto();
        searchDto.setType("ItemNewSpecial");
        searchDto.setMaxResult(4);
        return bookService.getData(searchDto,"https://www.aladin.co.kr/ttb/api/ItemList.aspx");
    }

    public List<BookDto> thismonthbook () {
        ApiSearchDto searchDto = new ApiSearchDto();
        searchDto.setType("ItemNewSpecial");
        searchDto.setStartIdx(3);
        searchDto.setMaxResult(8);
        return bookService.getData(searchDto,"https://www.aladin.co.kr/ttb/api/ItemList.aspx");
    }
}
