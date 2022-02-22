package com.project.bookjuck;

import com.project.bookjuck.email.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class BookjuckApplication {

    @Autowired
    private EmailSendService service;




    public static void main(String[] args) {
        SpringApplication.run(BookjuckApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        service.sendSimpleEmail("test"); }

}
