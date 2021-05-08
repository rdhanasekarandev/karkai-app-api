package com.karkai.service;

import com.karkai.modal.Contact;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class MailService {
    public String sendmail(Contact contact) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rdhanasekaran100199@gmail.com", "Dhana100%");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("rdhanasekarandeveloper@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rdhanasekarandeveloper@gmail.com"));
        msg.setSubject("KARKAI USER");

        String content = "Date :"+new Date()+"\n"+"Name :"+contact.getName()+"\n"+"Email :"+contact.getEmail()+"\n"+"Phone :"+contact.getPhone()+"\n"+"Message :"+contact.getMessage();

        msg.setContent(content, "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
        return "success";
    }

}
