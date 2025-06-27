package com.ty.NotificationModule.controller;

import com.ty.NotificationModule.dto.SuccessResponse;
import com.ty.NotificationModule.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping(path = "api/v1/notification")
@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping(path = "send-email")
    public SuccessResponse sendEmail(
            @RequestParam(name = "sender_email") String senderEmail,
            @RequestParam(name = "receiver_email") String receiverEmail) {
        // Logic to send the email
        return notificationService.sendEmail(senderEmail,receiverEmail);
    }
}
