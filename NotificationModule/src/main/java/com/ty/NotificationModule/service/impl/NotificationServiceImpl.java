package com.ty.NotificationModule.service.impl;

import com.ty.NotificationModule.dto.SuccessResponse;
import com.ty.NotificationModule.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    @KafkaListener(topics = "leave-events", groupId = "notification-group")
    public void handleLeaveNotification(String message) {
        System.out.println("Notification Service received: " + message);
        String[] parts = message.split(",");
        if (parts.length == 3) {
            String senderEmail = parts[0];
            String receiverEmail = parts[1];
            String data =parts[2];
            SuccessResponse response = sendEmail(senderEmail, receiverEmail);
            System.out.println(response.getData());
        } else {
            System.err.println("Invalid message format received: " + message);
        }
    }

    @Override
    public SuccessResponse sendEmail(String senderEmail, String receiverEmail) {
        return SuccessResponse.builder()
                .message("Email sent")
                .status(HttpStatus.OK)
                .data("Email sent to " + receiverEmail)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
