package com.javaschool.logistic.utils;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@PropertySource(value = { "classpath:email.properties" })
public class EmailService  {

    @Autowired
    private Environment environment;

    private static final Logger LOGGER = Logger.getLogger(EmailService.class);

    public void send(String text, String toEmail){
        Session session = Session.getInstance(getProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(environment.getProperty("email.username"), environment.getProperty("email.password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(environment.getProperty("email.username")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your account has been created!");
            message.setContent(text,"text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.warn(e);
        }
    }

    private Properties getProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

}
