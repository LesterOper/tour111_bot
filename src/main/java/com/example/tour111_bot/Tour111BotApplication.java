package com.example.tour111_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Tour111BotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Tour111BotApplication.class, args);
    }

}
