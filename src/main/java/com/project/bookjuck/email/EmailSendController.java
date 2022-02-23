package com.project.bookjuck.email;

import com.project.bookjuck.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmailSendController {
    @Autowired
    private EmailSendService service;

    @PostMapping("/sendEmail")
    @ResponseBody
    public Map<String, String> sendMail(@RequestBody UserDto dto){
        service.sendSimpleEmail(dto.getCurrentEmail());
        Map<String, String> result = new HashMap();
        result.put("result", "1");
        return result;
    }

}
