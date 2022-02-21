package com.project.bookjuck.email;

import com.project.bookjuck.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailSendController {
    @Autowired
    private EmailSendService service;

    @PostMapping("/sendEmail")
    public void sendEmail(UserDto dto){
        service.sendSimpleEmail(dto.getCurrnetEmail());
    }
}
