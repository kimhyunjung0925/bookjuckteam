package com.project.bookjuck.user;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}

