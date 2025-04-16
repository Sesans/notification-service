package com.ms.notificationservice.service;

import com.ms.notificationservice.domain.Email;

public interface EmailService {
    Email sendEmail(Email email);
}