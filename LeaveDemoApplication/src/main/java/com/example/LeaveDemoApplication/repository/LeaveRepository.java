package com.example.LeaveDemoApplication.repository;

import com.example.LeaveDemoApplication.dto.SuccessResponse;
import com.example.LeaveDemoApplication.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest,Integer> {

    List<LeaveRequest> findAllByEmployeeId(int empId);
    List<LeaveRequest> findAllById(int empId);
}
