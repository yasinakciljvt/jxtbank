package com.jxt.jxtbank;

import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.enums.NotificationType;
import com.jxt.jxtbank.notification.dto.NotificationDTO;
import com.jxt.jxtbank.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@RequiredArgsConstructor
@EnableAsync
@SpringBootApplication
public class JxtbankApplication {

	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(JxtbankApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args  -> {
			NotificationDTO notificationDTO = NotificationDTO.builder()
					.recipient("bmttechsolutions@gmail.com")
					.subject("Testing")
					.body("Hi man, its a testin mail")
					.notificationType(NotificationType.EMAIL).build();
			notificationService.sendEmail(notificationDTO, new User());
		};
	}

}
