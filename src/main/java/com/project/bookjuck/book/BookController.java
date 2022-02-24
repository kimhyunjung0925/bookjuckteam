package com.project.bookjuck.book;

import com.project.bookjuck.Const;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String bestList(Model model, ApiSearchDto searchDto) {
        List<BookDto> list = service.bestBookList(searchDto);
        model.addAttribute(Const.Category, "베스트도서");
        model.addAttribute("bestlist",list);
        return "book/list";
    }

    @GetMapping("/new")
    public String newList() {
        return "book/list";
    }
}
