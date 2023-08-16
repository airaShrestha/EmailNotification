package com.eficens.emailapp.service;

import com.eficens.emailapp.Model.EmailTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageConsumer {

    @JmsListener(destination = "aira.queue")
    public void receiveEmailMessage(String jsonMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EmailTemplate emailTemplate = objectMapper.readValue(jsonMessage, EmailTemplate.class);

            // Process the emailTemplate...
            System.out.println("Received Email: " + emailTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
