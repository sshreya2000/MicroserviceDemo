package com.example.LeaveDemoApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

}

