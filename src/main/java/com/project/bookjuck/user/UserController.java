package com.project.bookjuck.user;

import com.project.bookjuck.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/login")
    public void login(@ModelAttribute("UserEntity") UserEntity entity){

    }

    @GetMapping("/join")
    public void join(@ModelAttribute("entity") UserEntity entity){}

    @PostMapping("/join")
    public String joinProc(UserEntity entity){
        System.out.println(entity);
        int result = service.join(entity);
        return "user/joinDone";
    }

    @GetMapping("/leave")
    public String leave() {
        return "user/leave";
    }

    @GetMapping("/leaveDone")
    public String leaveDone(){ return "user/leaveDone"; }

    @GetMapping("mypage")
    public String mypage() {
        return "user/mypage";
    }

    @GetMapping("pcHistory")
    public String pcHistory() {
        return "user/pcHistory";
    }

    @GetMapping("changeInfo")
    public String changeInfo() {
        return "user/changeInfo";
    }

    @GetMapping("changePw")
    public String changePw() {
        return "user/changePw";
    }


    @ResponseBody
    @GetMapping("/idChk/{uid}")
    public Map<String, Integer> idChk(@PathVariable String uid) {
        System.out.println("uid :" + uid);
        Map<String, Integer> res = new HashMap<>();
        res.put("result", service.idChk(uid));
        return res;
    }

}

