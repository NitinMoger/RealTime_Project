package com.xworkz.finalProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    public MailConfiguration(){
        System.out.println("Created no argument constructor for MailConfiguration");
    }

    @Bean
    public JavaMailSender mailConfig(){
        JavaMailSenderImpl mailSender =new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("nitin.xworkz@gmail.com");
        mailSender.setPassword("bfva ntfo esag lkaz");
        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }
}

