package com.ty.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SuccessResponse {
    private String message;
    private HttpStatus status;
    private Object data;
    private LocalDateTime timestamp;
}
