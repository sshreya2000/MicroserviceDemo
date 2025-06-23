package com.ty.client;

import com.ty.dto.LeaveRequestDto;
import com.ty.dto.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "LEAVE-DEMO-APPLICATION")
public interface LeaveClient {
    @PostMapping("/api/v1/leave/apply")
    public SuccessResponse applyLeave(@RequestBody LeaveRequestDto request);
    @GetMapping("/api/v1/leave/employee/{empId}")
    public SuccessResponse getLeavesByEmployee(@PathVariable int empId);
}
