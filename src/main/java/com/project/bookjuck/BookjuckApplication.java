package com.project.bookjuck;

import com.project.bookjuck.email.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BookjuckApplication {

    @Autowired
    private EmailSendService service;

    public static void main(String[] args) {
        SpringApplication.run(BookjuckApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        service.sendSimpleEmail("khjung0925@naver.com"); //받는사람 입력
    }

}
