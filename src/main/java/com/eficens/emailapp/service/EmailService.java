package com.eficens.emailapp.service;

import com.eficens.emailapp.Model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service// This is not pojo. It is another way of creating object which may have some business perspective over it
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromAddress;
    public String sendEmail(EmailTemplate emailTemplate){
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setFrom(emailTemplate.getFromAddress());
            mailMessage.setTo(emailTemplate.getToAddress());
            mailMessage.setText(emailTemplate.getEmailBody());
            mailMessage.setSubject(emailTemplate.getSubject());
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
    public String sendEmailWithAttachment(EmailTemplate emailTemplate) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            // Setting multipart as true for attachments to be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromAddress);
            mimeMessageHelper.setTo(emailTemplate.getToAddress());
            mimeMessageHelper.setText(emailTemplate.getEmailBody());
            mimeMessageHelper.setSubject(emailTemplate.getSubject());
            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(emailTemplate.getFilePath()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "File has been sent Successfully to "+emailTemplate.getToAddress();
        } catch (MessagingException e) {
            return "Error while sending mail";
        }
    }



}
