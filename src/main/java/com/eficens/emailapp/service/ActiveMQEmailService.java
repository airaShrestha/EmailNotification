package com.eficens.emailapp.service;

import com.eficens.emailapp.Model.EmailTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class ActiveMQEmailService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private EmailService emailService;
    private static final String AIRA_QUEUE = "AiraQueue";

    public ActiveMQEmailService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String sendEmailToAiraQueue(EmailTemplate emailTemplate) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(emailTemplate);

            jmsTemplate.send("aira.queue", session -> {
                TextMessage message = session.createTextMessage(json);
                return message;
            });
            //emailService.sendEmail(emailTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }
        return emailService.sendEmail(emailTemplate);
    }



}
