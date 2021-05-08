package com.karkai.controller;


import com.karkai.modal.Contact;
import com.karkai.modal.Folder;
import com.karkai.service.MailService;
import com.karkai.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(value = "send")
public class MailController {

    @Autowired
    private MailService mailService;


    //  create new User
    @PostMapping("/doubt")
    public String sendDoubt(@RequestBody Contact contact) throws MessagingException, IOException {
        return mailService.sendmail(contact);
    }

    //  create new User
    @PostMapping("/contact")
    public String sendMessage(@RequestBody Contact contact) throws MessagingException, IOException {
        return mailService.sendmail(contact);
    }

}
