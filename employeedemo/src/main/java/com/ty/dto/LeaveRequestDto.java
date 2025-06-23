package com.ty.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class LeaveRequestDto {
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
