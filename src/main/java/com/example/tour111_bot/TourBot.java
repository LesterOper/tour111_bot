package com.example.tour111_bot;


import com.example.tour111_bot.sql.SQLSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TourBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUsername;
    private String botToken;

    @Autowired
    private SQLSupport sqlSupport;

    public TourBot(DefaultBotOptions botOptions) {
        super(botOptions);

    }
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        long chat_id = 0L;
        String info = "Сообщение неверно";
        if(update.getMessage() != null && update.getMessage().hasText()) {
            chat_id = update.getMessage().getChatId();
            info = sqlSupport.getInfoByCityName(update.getMessage().getText());
            if(info == null) {
                info = "Данного города нет в базе данных";
            }
            return new SendMessage(chat_id, info);
        }
        return new SendMessage(chat_id, info);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
