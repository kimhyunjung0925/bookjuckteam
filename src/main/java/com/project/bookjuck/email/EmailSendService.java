package com.project.bookjuck.email;

import com.project.bookjuck.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class EmailSendService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
            private HttpSession hs;


    public int sendSimpleEmail(String currentEmail) {
        final int ePw = createKey();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bookjuck96@gmail.com");            //메일 보내는 사람
        message.setTo(currentEmail);                         //메일 받는 사람
        message.setText("인증코드는 " + ePw + " 입니다");  //메일 내용
        message.setSubject("북적북적 인증코드 메일 발송");  //메일 제목

        mailSender.send(message);
        hs.setAttribute("tempEmailKey",ePw); //세션에서 이메일 인증을 할 수도 있어서 남겼습니다.
        //세션에 이메일 인증 코드를 담는 부분입니다.
        return ePw; // 굳이 필요없지만 지우지 않는 부분.
    }

    //인증키 번호 만들기
    public static int createKey() {
        return (int)(Math.random()*1000000);
    }

    //입력일치 함수
    public void correctKey(){
        //여기서 패치로 받아와야 할거같다.
    }



}
