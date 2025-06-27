package com.ty.controller;

import com.ty.dto.EmployeeRequest;
import com.ty.dto.LeaveRequestDto;
import com.ty.dto.SuccessResponse;
import com.ty.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee/v1")
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping(path = "")
    public SuccessResponse registerEmployee(@RequestBody EmployeeRequest request) {

        Integer id = employeeService.registerEmployee(request);
        return SuccessResponse.builder()
                .message("Employee registered")
                .status(HttpStatus.CREATED)
                .data(id)
                .timestamp(LocalDateTime.now())
                .build();

    }

    @PostMapping("/{id}/apply-leave")
    public SuccessResponse applyLeave(@PathVariable int id, @RequestBody LeaveRequestDto dto) {
        return SuccessResponse.builder()
                .data(employeeService.sendLeaveRequest(id, dto))
                .message("Leave applied")
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}/leaves")
    public  SuccessResponse getLeaves(@PathVariable int id) throws InterruptedException {
        return SuccessResponse.builder()
                .data(employeeService.fetchLeavesOfEmployee(id))
                .message("Leaves for the employee")
                .status(HttpStatus.CREATED)
                .build();

    }

}
