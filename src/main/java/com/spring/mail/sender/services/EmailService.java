package com.spring.mail.sender.services;

import java.io.File;

public interface EmailService {

    void sendEmail(String[] user, String subject, String message);

    void SendEmailWithFile(String[] user, String subject, String message, File file);
}
