package com.eficens.emailapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class EmailApp {
    public static void main(String[] args) {
        SpringApplication.run(EmailApp.class,args);
        //System.out.println("Hello world!");
    }
}