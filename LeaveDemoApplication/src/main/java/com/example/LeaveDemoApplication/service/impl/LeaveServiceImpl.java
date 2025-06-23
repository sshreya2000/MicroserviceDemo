package com.example.LeaveDemoApplication.service.impl;

import com.example.LeaveDemoApplication.dto.SuccessResponse;
import com.example.LeaveDemoApplication.entity.LeaveRequest;
import com.example.LeaveDemoApplication.entity.LeaveStatus;
import com.example.LeaveDemoApplication.repository.LeaveRepository;
import com.example.LeaveDemoApplication.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;

    @Override
    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(request);
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployeeId(int empId) {
        return leaveRepository.findAllByEmployeeId(empId);
    }

    @Override
    public LeaveRequest approveLeave(int leaveId) {
        LeaveRequest req = leaveRepository.findById(leaveId).orElseThrow();
        req.setStatus(LeaveStatus.APPROVED);
        return leaveRepository.save(req);
    }
}
