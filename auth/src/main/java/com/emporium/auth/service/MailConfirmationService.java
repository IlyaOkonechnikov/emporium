package com.emporium.auth.service;

import com.emporium.auth.config.MailPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Slf4j
@Service
public class MailConfirmationService {

    private final MailPropertiesConfig mailPropertiesConfig;
    private final String letter;
    private final Properties properties;

    @Autowired
    public MailConfirmationService(MailPropertiesConfig mailPropertiesConfig,
                                   @Value("${mail.letter}") String letter,
                                   @Qualifier("mailProperties") Properties properties) {
        this.mailPropertiesConfig = mailPropertiesConfig;
        this.letter = letter;
        this.properties = properties;

    }

    public void sendConfirmationMail(String name, String email, ObjectId id){
        log.debug("sendConfirmationMail() - start. name: {}, email: {}",name,email);
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailPropertiesConfig.getFrom()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mail-confirmation letter");
            String msg = "Hello, " + name + letter + id.toString();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message, mailPropertiesConfig.getMailUsername(), mailPropertiesConfig.getMailPassword());
            log.debug("sendConfirmationMail() - end. email: {}",email);

        } catch (MessagingException e) {
            log.error("Failed to send mail-confirmation letter to email {}\n" + e.getMessage(), e,email);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
