package com.example.tour111_bot.controller;

import com.example.tour111_bot.TourBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class BotController {

    private final TourBot tourBot;
    public BotController(TourBot bot) {
        tourBot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        if(update.getMessage().getText().equals("/start")) {
            return new SendMessage(update.getMessage().getChatId(), "Enter some city to get information");
        }
        return tourBot.onWebhookUpdateReceived(update);
    }
}
