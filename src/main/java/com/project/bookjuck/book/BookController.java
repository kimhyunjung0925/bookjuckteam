package com.project.bookjuck.book;

import com.project.bookjuck.Const;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.book.model.bookinfo.BookEntity;
import com.project.bookjuck.book.model.bookinfo.BookSubInfoEntity;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/detail")
    public String detail(ApiSearchDto searchDto, Model model) {
        BookEntity book = service.bookDetail(searchDto);
        model.addAttribute("list",book);
        return "book/detail";
    }

    @GetMapping("/list")
    public String list(BookDto dto, Model model, ApiSearchDto apidto) {
        List<BookDto> list = service.sel(dto);
        model.addAttribute("list", list);
        model.addAttribute("category", apidto.getCategory());
        model.addAttribute("bookCate",dto.getCategoryBookjuck());
        return "book/list";
    }



    @GetMapping("/best")
    public String bestList(ApiSearchDto searchDto,@ModelAttribute(value="selectVal") String selectVal, Model model) {
        searchDto.setCategory("best"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.bestBookList(searchDto);
        model.addAttribute("list", list);
        model.addAttribute("bestCate",searchDto.getSearchTarget());
        return "book/best";
    }


    @GetMapping("/new")
    public String newList(@ModelAttribute("searchDto") ApiSearchDto searchDto, Model model) {
        searchDto.setCategory("new"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.newBookList(searchDto);
        model.addAttribute("list", list);
        return "book/new";
    }

    @GetMapping("/search")
    public String searchresut(@ModelAttribute("searchDto") ApiSearchDto searchDto, Model model) {
        searchDto.setCategory("search"); //list.html 링크 변수에 넣기 위해서 따로 넣어줌. category에 겟매핑 주소 쓰면 됨
        List<BookDto> list = service.searchBookList(searchDto);
        model.addAttribute(Const.Category, searchDto.getSearchWord());
        model.addAttribute("list", list);
        return "book/search";
    }



    @ResponseBody
    @GetMapping("/kor")
    public List<BookDto> bestListApi(@RequestParam("selectVal") String selectVal, @RequestParam("categoryBookjuck") String categoryBookjuck) {

        BookDto dto = new BookDto();
        dto.setSelectVal(selectVal);
        dto.setCategoryBookjuck(categoryBookjuck);
        List<BookDto> list = service.sel2(dto);


        return list;
    }


}
