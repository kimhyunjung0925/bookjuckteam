package com.project.bookjuck.user.emailJavaxMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailSend {
    @Autowired
    private JavaMailSender mailSender;

    public static final String ePw = createKey();

    public void sendSimpleEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bookjuck@gmail.com");            //메일 보내는 사람
        message.setTo(toEmail);                         //메일 받는 사람
        message.setText("인증코드는 " + ePw + " 입니다");  //메일 내용
        message.setSubject("북적북적 인증코드 메일 발송");  //메일 제목

        mailSender.send(message);
        System.out.println("-------메일 보내기 성공!!------");
    }

    //인증키 번호 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

}
