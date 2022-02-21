package com.project.bookjuck.user.emailJavaxMail;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
   private final  String user = "mpage3523@gmail.com";
    private  final  String password = "mpage@741"; //나중에 반드시 지움

    public Session session(){


        // 2. Property에 SMTP 서버 정보 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        return session;
    }

    public int randCode(){
        return (int)(Math.random()*1000000);
    }

    public void sendMail(String costomerEmail){
        MimeMessage message = new MimeMessage(session());
        try {
            message.setFrom(new InternetAddress(user));

            // 수신자 메일 주소(받는 사람)
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(costomerEmail));

            // Subject
            message.setSubject("PLAYDDIT verification code");

            // Text
            message.setText("북적북적의 인증번호는 ["+randCode()+"] 입니다.");

            Transport.send(message);    // send message


        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
