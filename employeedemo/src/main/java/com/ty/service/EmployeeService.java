package com.ty.service;

import com.ty.dto.EmployeeRequest;
import com.ty.dto.LeaveRequestDto;


public interface EmployeeService {
    Integer registerEmployee(EmployeeRequest request);

    Object sendLeaveRequest(int id, LeaveRequestDto dto);

    Object fetchLeavesOfEmployee(int id) throws InterruptedException;
}
