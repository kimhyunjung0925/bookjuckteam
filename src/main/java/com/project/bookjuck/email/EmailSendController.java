package com.project.bookjuck.email;

import com.project.bookjuck.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sendEmail")
public class EmailSendController {
    @Autowired
    private EmailSendService service;

    @PostMapping
    public void sendEmail(@RequestBody UserDto dto){
        service.sendSimpleEmail(dto.getCurrentEmail());
        System.out.println(dto.getCurrentEmail());
    }
}
