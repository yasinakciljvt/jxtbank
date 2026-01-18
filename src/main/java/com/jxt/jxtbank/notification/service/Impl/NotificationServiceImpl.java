package com.jxt.jxtbank.notification.service.Impl;

import com.jxt.jxtbank.auth_users.entity.User;
import com.jxt.jxtbank.enums.NotificationType;
import com.jxt.jxtbank.notification.dto.NotificationDTO;
import com.jxt.jxtbank.notification.entity.Notification;
import com.jxt.jxtbank.notification.repository.NotificationRepository;
import com.jxt.jxtbank.notification.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendEmail(NotificationDTO notificationDTO, User user) {

        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED ,
                    StandardCharsets.UTF_8.name());
            helper.setTo(notificationDTO.getRecipient());
            helper.setSubject(notificationDTO.getSubject());

            if(notificationDTO.getTemplateName() != null){
                Context context =new Context();
                context.setVariables(notificationDTO.getTemplateVariables());
                String htmlContext = templateEngine.process(notificationDTO.getTemplateName(), context);
                helper.setText(htmlContext, true);

            }else {
                helper.setText(notificationDTO.getBody(), true);
            }
            mailSender.send(mimeMessage);


            Notification notification = Notification.builder()
                    .recipient(notificationDTO.getRecipient())
                    .subject(notificationDTO.getSubject())
                    .body(notificationDTO.getBody())
                    .notificationType(NotificationType.EMAIL)
                    .user(user)
                    .build();
            repository.save(notification);




        }catch (MessagingException e){

            log.error(e.getMessage());
        }

    }

}
