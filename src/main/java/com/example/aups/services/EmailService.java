package com.example.aups.services;

import com.example.aups.exceptions.TextEmailException;
import com.example.aups.models.MailHeader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTextEmailNoAttachment(MailHeader mailHeader, String message) {
        this.mailSender.send(createMessage(mailHeader, message));
    }

    public MimeMessage createMessage(MailHeader mailHeader, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage, "UTF-8");
            email.setTo(mailHeader.getRecipient());
            email.setFrom(mailHeader.getSender());
            email.setSubject(mailHeader.getSubject());
            email.setText(message, false);
            return mimeMessage;
        } catch (MessagingException | IllegalArgumentException e) {
            throw new TextEmailException();
        }
    }
}
