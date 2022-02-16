package com.project.bookjuck.ajax;

import com.project.bookjuck.cscenter.CsCenterService;
import com.project.bookjuck.cscenter.model.FaqEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/ajax/cscenter")
public class CsCenterRestController {
    @Autowired
    private CsCenterService service;

    //faq리스트 가져오기
    @GetMapping("/faq")
    public List<FaqEntity> selFaqList(){
        return service.selFaqList();
    }
}
