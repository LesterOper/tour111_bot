package com.example.tour111_bot;

import com.example.tour111_bot.TourBot;
import com.example.tour111_bot.sql.SQLSupport;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class ConfigApplication {
    private String webHookPath;
    private String botUsername;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public TourBot MyTourBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        TourBot bot = new TourBot(options);
        bot.setWebHookPath(webHookPath);
        bot.setBotToken(botToken);
        bot.setBotUsername(botUsername);
        return bot;
    }

    @Bean
    public SQLSupport MySqlSupport() {
        return new SQLSupport();
    }
}
