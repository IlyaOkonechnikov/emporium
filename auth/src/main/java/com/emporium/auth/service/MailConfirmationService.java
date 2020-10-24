package com.emporium.auth.service;

import com.emporium.auth.config.MailConfirmationConfig;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
@Slf4j
@Service
public class MailConfirmationService {

    private final MailConfirmationConfig mailConfirmationConfig;
    private final String letter;

    @Autowired
    public MailConfirmationService(MailConfirmationConfig mailConfirmationConfig,
                                   @Value("${mail.letter}") String letter) {
        this.letter = letter;
        this.mailConfirmationConfig = mailConfirmationConfig;
    }

    public void sendMailConfirmLetter(String name, String email, ObjectId id){
        log.debug("sendMailConfirmLetter() - start. name: {}, email: {}",name,email);
        Session session = Session.getInstance(mailConfirmationConfig.getProperty());
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailConfirmationConfig.getFrom()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mail-confirmation letter");
            String msg = "Hello, " + name + letter + id.toString();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message, mailConfirmationConfig.getMailUsername(), mailConfirmationConfig.getMailPassword());
            log.debug("sendMailConfirmLetter - end. email: {}",email);

        } catch (MessagingException e) {
            log.error("Failed to send mail-confirmation letter to email {}\n" + e.getMessage(), e,email);
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage(), e);
        }
    }
}
