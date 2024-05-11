package org.example.payroll_management.service;


import org.example.payroll_management.model.Leave;
import org.example.payroll_management.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // write methods here
    public void saveLeave(Leave leave) {
        leaveRepository.save(leave);
    }
    public List<Leave> getLeaves() {
        return leaveRepository.findAll();
    }
}
