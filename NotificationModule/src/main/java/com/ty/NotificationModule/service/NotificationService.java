package com.ty.NotificationModule.service;

import com.ty.NotificationModule.dto.SuccessResponse;

public interface NotificationService {
    SuccessResponse sendEmail(String senderEmail, String receiverEmail);
}
