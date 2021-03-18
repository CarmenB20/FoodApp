package com.bestapp.ordersapp.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSenderImpl mailSender;

    @Autowired
    public EmailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    @Async
    public void send(String to, String email) {
        try{

            //
            Session session = Session.getInstance(this.mailSender.getJavaMailProperties(),
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("javatest487@gmail.com", "testjava123");
                        }
                    });
            MimeMessage mimeMessage = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Welcome to your new account!");
            helper.setFrom("javatest487@gmail.com");

            mailSender.send(mimeMessage);

        }catch (MessagingException e){
            LOGGER.error("failed to send email", e);
            throw  new IllegalStateException("Failed to send email");
        }

    }

}

