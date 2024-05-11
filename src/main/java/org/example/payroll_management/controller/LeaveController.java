package org.example.payroll_management.controller;

import org.example.payroll_management.model.Leave;
import org.example.payroll_management.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class LeaveController {
    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/")
    public List<Leave> getLeaves() {
        return leaveService.getLeaves();
    }
}
