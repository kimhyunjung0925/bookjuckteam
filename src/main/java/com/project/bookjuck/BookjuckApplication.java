package com.project.bookjuck;

import com.project.bookjuck.email.EmailSendService;
import com.project.bookjuck.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class BookjuckApplication {

    @Autowired
    private EmailSendService service;

    public static void main(String[] args) {
        SpringApplication.run(BookjuckApplication.class, args);
    }

    @Controller
    public static class MainRedirectController {
        @GetMapping("/")
        public String redirectToMain() {
            return "/main";
        }
    }

}
