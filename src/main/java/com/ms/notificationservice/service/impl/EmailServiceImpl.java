package com.ms.notificationservice.service.impl;

import com.ms.notificationservice.domain.Email;
import com.ms.notificationservice.repository.EmailRepository;
import com.ms.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    @Override
    public Email sendEmail(Email email) {
        try {
            email.setSubmissionDate(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailFrom);
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), true);
            mailSender.send(message);

        }catch (MailException | MessagingException e){
            throw new RuntimeException(e);
        }
        return emailRepository.save(email);
    }
}