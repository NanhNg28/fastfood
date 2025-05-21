package com.nanhng.FastFood.other_service.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    private JavaMailSender emailSender;

}
