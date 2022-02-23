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
    public Map<String, Integer> sendMail(@RequestBody UserDto dto){
        int temp = service.sendSimpleEmail(dto.getCurrentEmail());

        Map<String, Integer> result = new HashMap();
        result.put("result",temp);
        return result;
    }
//sendsimpe 함수가 int 라서 map 도 int 로 바꿨습니다.
}
