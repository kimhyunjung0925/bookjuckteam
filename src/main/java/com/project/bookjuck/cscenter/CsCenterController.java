package com.project.bookjuck.cscenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cscenter")
public class CsCenterController {

    @GetMapping
    public String CsCenter() {
        return "cscenter/cscenter";
    }

    @GetMapping("/notice")
    public String CsNotice() {
        return "cscenter/notice";
    }

    @GetMapping("/complaint")
    public String comPlaint() {
        return "cscenter/complaint";
    }

    @GetMapping("/faq")
    public String FAQ() {
        return "cscenter/faq";
    }

}
