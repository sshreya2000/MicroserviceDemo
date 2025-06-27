package com.ty.client;

import com.ty.dto.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NOTIFICATION-MODULE")
public interface NotificationClient {

    @PostMapping("/api/v1/notification/send-email")
    public SuccessResponse sendEmail(
            @RequestParam(name = "sender_email") String senderEmail,
            @RequestParam(name = "receiver_email") String receiverEmail);
}
