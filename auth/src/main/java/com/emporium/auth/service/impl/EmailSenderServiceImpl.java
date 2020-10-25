package com.emporium.auth.service.impl;

import com.emporium.auth.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final String MSG_TEMPLATE = "Nice to meet you, %s!\nWe are glad that you have decided to use our " +
            "service. Please confirm your email by following the link: %s/%s";

    private final String confirmationUrl;
    private final JavaMailSender emailSender;

    public EmailSenderServiceImpl(@Value("${url.email-confirmation}") String confirmationUrl,
                                  JavaMailSender emailSender) {
        this.confirmationUrl = confirmationUrl;
        this.emailSender = emailSender;
    }

    public void sendConfirmationEmail(ObjectId id, String name, String email) {
        log.debug("sendConfirmationMail() - start. name: {}, email: {}", name, email);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //noinspection ConstantConditions
            message.setFrom(((JavaMailSenderImpl) emailSender).getUsername());
            message.setTo(email);
            message.setSubject("Emporium email confirmation.");
            message.setText(String.format(MSG_TEMPLATE, name, confirmationUrl, id));
            emailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send a confirmation email to: " + email + "\n" + e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}