package com.project.bookjuck.main;

import com.project.bookjuck.WebSecurityConfig;
import com.project.bookjuck.book.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@Controller
//public class MainController {
//
//    @Autowired
//    private MainService service;
//
//    @GetMapping("/main")
//    public String main(Model model) {
//        List<BookDto> domesticlist = service.domesticbook();
//        List<BookDto> foreignlist = service.foreignbook();
//        List<BookDto> newlist = service.newbook();
//        List<BookDto> stedylist = service.steadybook();
//        List<BookDto> monthBook = service.thismonthbook();
//        model.addAttribute("domesticlist", domesticlist);
//        model.addAttribute("foreignlist", foreignlist);
//        model.addAttribute("newlist", newlist);
//        model.addAttribute("stedylist", stedylist);
//        model.addAttribute("monthBook", monthBook);
//
//        return "main";
//    }
//}

@Controller
public class MainController {

    @Autowired
    private MainService service;

    @GetMapping("/main")
    public String main(Model model) {
        List<BookDto> domesticlist = service.domesticbook();
        List<BookDto> foreignlist = service.foreignbook();
        List<BookDto> newlist = service.newbook();
        List<BookDto> stedylist = service.steadybook();
        List<BookDto> monthBook = service.thismonthbook();
        model.addAttribute("domesticlist", domesticlist);
        model.addAttribute("foreignlist", foreignlist);
        model.addAttribute("newlist", newlist);
        model.addAttribute("stedylist", stedylist);
        model.addAttribute("monthBook", monthBook);

        return "main";
    }
}

