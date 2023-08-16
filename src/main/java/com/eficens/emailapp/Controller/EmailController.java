package com.eficens.emailapp.Controller;

import com.eficens.emailapp.Model.EmailTemplate;
import com.eficens.emailapp.service.ActiveMQEmailService;
import com.eficens.emailapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/*To send out an email following info is required:(These details can be exposed to a person)
From
To
Subject
Email Body
Is attachment required?(not required for customer but for promo)*/
@RestController//to make it work as restAPI
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private ActiveMQEmailService activeMQEmailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailTemplate emailTemplate){
        return emailService.sendEmail(emailTemplate);
    }

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailTemplate emailTemplate){
        return emailService.sendEmailWithAttachment(emailTemplate);
    }

    @PostMapping("/sendToAiraQueue")
    @Transactional
    public String sendToAiraQueue(@RequestBody EmailTemplate emailTemplate) {
        // Call the service method to send the email template to the AiraQueue
        return activeMQEmailService.sendEmailToAiraQueue(emailTemplate);
        // "Email request sent to AiraQueue";
    }
}
