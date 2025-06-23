package com.example.LeaveDemoApplication.controller;

import com.example.LeaveDemoApplication.dto.SuccessResponse;
import com.example.LeaveDemoApplication.entity.LeaveRequest;
import com.example.LeaveDemoApplication.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/v1/leave"})
public class LeaveController {
    private final LeaveService leaveService;
    @PostMapping("/apply")
    public SuccessResponse applyLeave(@RequestBody LeaveRequest request) {
        LeaveRequest leaveRequest=leaveService.applyLeave(request);
        return SuccessResponse.builder()
                .data(leaveRequest)
                .message("Leave applied")
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/employee/{empId}")
    public SuccessResponse getLeavesByEmployee(@PathVariable int empId) {
        List<LeaveRequest> leaveRequest= leaveService.getLeavesByEmployeeId(empId);
        return SuccessResponse.builder()
                .data(leaveRequest)
                .message("Leaves of employee")
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/approve/{id}")
    public SuccessResponse approveLeave(@PathVariable int id) {
        LeaveRequest request= leaveService.approveLeave(id);
        return SuccessResponse.builder()
                .data(request)
                .message("Leave applied")
                .status(HttpStatus.CREATED)
                .build();
    }
}
