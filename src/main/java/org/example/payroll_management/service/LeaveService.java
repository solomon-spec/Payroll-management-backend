package org.example.payroll_management.service;


import jakarta.persistence.criteria.Predicate;
import org.example.payroll_management.model.Leave;
import org.example.payroll_management.model.LeaveStatus;

import org.example.payroll_management.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public Leave createLeaveRecord(Leave leave) {
        return leaveRepository.save(leave);
    }

    public Leave getLeaveRecordById(Long id) {
        return leaveRepository.findById(id).orElse(null);
    }

    public Leave updateLeaveRecord(Long id, Leave leave) {
        leave.setId(id);
        return leaveRepository.save(leave);
    }

    public String deleteLeaveRecord(Long id) {
        leaveRepository.deleteById(id);
        return "Leave record deleted successfully";
    }

    public List<Leave> getLeaveRecordsByEmployeeId(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    public Leave cancelLeaveRequest(Long id) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            if (leave.getStatus() == LeaveStatus.PENDING || (leave.getStatus() == LeaveStatus.APPROVED && leave.getStartDate().isAfter(leave.getStartDate()))) {
                leaveRepository.deleteById(id);
                return leave;
            }
            else if (leave.getStatus() == LeaveStatus.APPROVED && leave.getEndDate().isAfter(LocalDate.now())) {
                leave.setEndDate(LocalDate.now());
                leaveRepository.save(leave);
                return leave;
            }
        }
        return null;
    }

    public Leave changeLeaveStatus(Long id, LeaveStatus status) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(status);
            leaveRepository.save(leave);
            return leave;

        }
        return null;
    }

    public int getLeaveBalance(Long employeeId) {
        List<Leave> leaves = leaveRepository.findByEmployeeId(employeeId);
        int balance = 0;
        for (Leave leave : leaves) {
            if (leave.getStatus() == LeaveStatus.APPROVED && leave.getStartDate().getYear() == LocalDate.now().getYear()) {
                balance += leave.getEndDate().getDayOfYear() - leave.getStartDate().getDayOfYear() + 1;
            }
        }
        return balance;
    }

    public List<Leave> getAllLeaveRecords() {
        return leaveRepository.findAll();
    }

    public List<Leave> searchLeave(Long employeeId, LocalDate startDate, LocalDate endDate, LeaveStatus status) {
        return leaveRepository.findAll((root, query , criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (employeeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("employee").get("id"), employeeId));
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
