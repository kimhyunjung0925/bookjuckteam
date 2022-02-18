package com.project.bookjuck.user;

import com.project.bookjuck.Const;
import com.project.bookjuck.user.model.UserDto;
import com.project.bookjuck.user.model.UserEntity;
import com.project.bookjuck.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public void join(@ModelAttribute("entity") UserVO vo){}

    @PostMapping("/join")
    public String joinProc(UserVO vo){
        System.out.println(vo);
        int result = service.join(vo);
        return "user/joinDone";
    }

    @GetMapping("/logout")
    public String logout(HttpSession hs){
        hs.invalidate();
        return "redirect:/main";
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

    @GetMapping("/mypage/changeInfo")
    public String changeInfo() {
        return "user/mypage/changeInfo";
    }



    @ResponseBody
    @GetMapping("/idChk/{uid}")
    public Map<String, Integer> idChk(@PathVariable String uid) {
        System.out.println("uid :" + uid);
        Map<String, Integer> res = new HashMap<>();
        res.put("result", service.idChk(uid));
        return res;
    }

    //--------------비밀번호 변경
    @GetMapping("/mypage/changePw")
    public void changePw(){}

    @PostMapping("/mypage/changePw")
    public String changePwProc(UserDto dto, RedirectAttributes rtta) {
        int result = service.changePw(dto);
        if(result != 1) {
            System.out.println(result);
            switch(result) {
                case 0:
                    rtta.addFlashAttribute(Const.MSG, "비밀번호 변경에 실패하였습니다.");
                    break;
                case 2:
                    rtta.addFlashAttribute(Const.MSG, "현재 비밀번호를 확인해 주세요.");
                    break;
            }
            return "redirect:/user/mypage/changePw";
        }
        return "redirect:/user/logout";
    }


}

