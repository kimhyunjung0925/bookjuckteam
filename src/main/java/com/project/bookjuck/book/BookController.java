package com.project.bookjuck.book;

import com.project.bookjuck.Const;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/detail")
    public String detail() {

        return "book/detail";
    }

    @GetMapping("/list")
    public String list() {
        return "book/list";
    }

    @GetMapping("/best")
    public String bestList(@ModelAttribute("searchDto") ApiSearchDto searchDto, Model model) {
        searchDto.setCategory("best"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.bestBookList(searchDto);
        model.addAttribute(Const.Category, "베스트도서");
        model.addAttribute("bestlist",list);
        return "book/list";
    }

    @GetMapping("/new")
    public String newList(@ModelAttribute("searchDto") ApiSearchDto searchDto, Model model) {
        searchDto.setCategory("new"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.newBookList(searchDto);
        model.addAttribute(Const.Category, "신간도서");
        model.addAttribute("newlist",list);
        return "book/list";
    }

    @GetMapping("/search")
    public String searchresut(@ModelAttribute("searchDto") ApiSearchDto searchDto, Model model) {
        searchDto.setCategory("search"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.searchBookList(searchDto);
        model.addAttribute(Const.Category, searchDto.getSearchWord()+"의 검색결과");
        model.addAttribute("searchlist", list);
        return "book/list";
    }
}
