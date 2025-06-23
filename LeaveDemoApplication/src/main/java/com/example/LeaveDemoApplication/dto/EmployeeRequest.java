package com.example.LeaveDemoApplication.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeRequest {
    private String firstname;
    private String lastname;
    private String email;
}
