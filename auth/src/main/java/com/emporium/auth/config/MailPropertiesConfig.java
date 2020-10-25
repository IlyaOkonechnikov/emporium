package com.emporium.auth.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Slf4j
@Component
@Getter
public class MailPropertiesConfig {

    private final String mailHost;
    private final String mailPort;
    private final String mailUsername;
    private final String mailPassword;
    private final String from;
    private final String socketFactoryPort;
    private final String socketFactoryClass;
    private final Properties property;

    public MailPropertiesConfig(@Value("${mail.host}") String mailHost,
                                @Value("${mail.port}") String mailPort,
                                @Value("${mail.username}")String mailUsername,
                                @Value("${mail.password}")String mailPassword,
                                @Value("${mail.from}")String from,
                                @Value("${mail.socket-port}")String socketFactoryPort,
                                @Value("${mail.socket-class}")String socketFactoryClass) {
        this.mailHost = mailHost;
        this.mailPort = mailPort;
        this.mailUsername = mailUsername;
        this.mailPassword = mailPassword;
        this.from = from;
        this.socketFactoryPort = socketFactoryPort;
        this.socketFactoryClass = socketFactoryClass;
        this.property = new Properties();
    }

    @Bean(name = "mailProperties")
    @ConfigurationProperties(prefix = "mail")
    private Properties buildProperty() {
        property.put("mail.smtp.host", mailHost);
        property.put("mail.smtp.port", mailPort);
        property.put("mail.smtp.socketFactory.port", socketFactoryPort);
        property.put("mail.smtp.socketFactory.class", socketFactoryClass);
        log.info("'mail.stmp.port' - {}",mailPort);
        log.info("'mail.stmp.socketFactory.port' - {}",socketFactoryPort);
        log.info("'mail.stmp.socketFactoty.class' - {}",socketFactoryClass);
        log.info("Letter sent from - {}",from);
        log.info("Mail Username - {}",mailUsername);
        log.info("Mail Password - {}",mailPassword);
        return property;
    }
}
