package com.project.bookjuck.user;

import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.Const;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.cscenter.model.ComplaintEntity;
import com.project.bookjuck.user.model.UserDto;
import com.project.bookjuck.user.model.UserEntity;
import com.project.bookjuck.user.model.UserVO;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    AuthenticationFacade authenticationFacade;


    //---------------로그인
    @GetMapping("/login")
    public void login(@ModelAttribute("UserEntity") UserEntity entity){
    }

    //------------회원가입
    @GetMapping("/join")
    public void join(@ModelAttribute("entity") UserVO vo){}

    @PostMapping("/join")
    public String joinProc(UserVO vo){
        int result = service.join(vo);
        return "user/joinDone";
    }

    //---------------로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession hs){
        hs.invalidate();
        return "redirect:/main";
    }

    //--------------회원 탈퇴
    @GetMapping("/leave")
    public String leave() { return "user/leave"; }

    @PostMapping("/leave")
    public String leaveProc(UserDto dto, RedirectAttributes rtta) {
        int result = service.leaveUser(dto);
        if(result != 1) {
            switch(result) {
                case 0:
                    rtta.addFlashAttribute(Const.MSG, "비밀번호 변경에 실패하였습니다.");
                    break;
                case 2:
                    rtta.addFlashAttribute(Const.MSG, "현재 비밀번호를 확인해 주세요.");
                    break;
            }
            return "redirect:/user/leave";
        }
        //주소만을 위해서 새로운 서비스가 필요할 것 같다.
        return "user/leave";
    }

    @GetMapping("/leaveDone")
    public String leaveDone(){ return "user/leaveDone"; }


    //-------------
    @GetMapping("mypage")
    public String mypage() {
        return "user/mypage";
    }

    @GetMapping("pcHistory")
    public String pcHistory() {
        return "user/pcHistory";
    }


    //--------------내 정보 수정
    @GetMapping("/mypage/changeInfo")
    public String changeInfo() {
        return "user/mypage/changeInfo";
    }

    @PostMapping("/mypage/changeInfo")
    public String changeInfoProc(UserEntity entity) {
        int result = service.changeUserInfo(entity);
        //주소만을 위해서 새로운 서비스가 필요할 것 같다.
        return "user/mypage/changeInfo";
    }


    //-------------내 문의
    @GetMapping("/mypage/myComplain")
    public String myComplain(ComplaintEntity entity, Model model) {
        List<ComplaintEntity> list = service.selComplain(entity);
        model.addAttribute(Const.Complain, list);
        return "user/mypage/myComplain";
    }

    @PostMapping("/mypage/myComplain")
    public String myComplainProc() {
        return "user/mypage/myComplain";
    }


    //------------아이디 체크
    @ResponseBody
    @GetMapping("/idChk/{uid}")
    public Map<String, Integer> idChk(@PathVariable String uid) {

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

