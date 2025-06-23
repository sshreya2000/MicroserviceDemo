package com.example.LeaveDemoApplication.service;

import com.example.LeaveDemoApplication.dto.SuccessResponse;
import com.example.LeaveDemoApplication.entity.LeaveRequest;

import java.util.List;

public interface LeaveService {
    LeaveRequest applyLeave(LeaveRequest request);

    List<LeaveRequest> getLeavesByEmployeeId(int empId);

    LeaveRequest approveLeave(int id);
}
