package com.project.bookjuck.user.emailJavaxMail;

import com.project.bookjuck.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmailController {

    @Autowired private EmailService emailService;

    @PostMapping("/sendMail")
    @ResponseBody
    public Map<String, String> sendMail(@RequestBody UserEntity entity){
        System.out.println(entity);
        emailService.sendMail(entity.getEmail());
        Map<String, String> result = new HashMap();
        result.put("result", "1");
        return result;
    }
}
