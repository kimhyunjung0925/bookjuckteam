package com.project.bookjuck.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/detail")
    public String detail() {
        return "book/detail";
    }

    @GetMapping("/list")
    public String list() {
        return "book/list";
    }
}
