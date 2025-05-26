package com.ms.notificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class NotificationServiceApplicationTests {

	@MockBean
	private RabbitTemplate rabbitTemplate;

	@MockBean
	private JavaMailSender javaMailSender;

	@Test
	void contextLoads() {
	}

}