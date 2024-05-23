package org.example.payroll_management.service;


import jakarta.persistence.criteria.Predicate;
import org.example.payroll_management.dto.LeaveDTO;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.Leave;
import org.example.payroll_management.model.LeaveStatus;

import org.example.payroll_management.repository.EmployeeRepository;
import org.example.payroll_management.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }



    public Leave convertToEntity(LeaveDTO leaveDTO) {
        Leave leave = new Leave();

        Employee employee = employeeRepository.findById(leaveDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setId(leaveDTO.getId());
        leave.setEmployee(employee);
        leave.setStartDate(leaveDTO.getStartDate());
        leave.setEndDate(leaveDTO.getEndDate());
        leave.setLeaveType(leaveDTO.getLeaveType());
        leave.setStatus(leaveDTO.getStatus());

        return leave;
    }

    public LeaveDTO convertToDTO(Leave leave) {
        return new LeaveDTO(
                leave.getId(),
                leave.getEmployee().getId(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getLeaveType(),
                leave.getStatus()
        );
    }

    public LeaveDTO createLeaveRecord(LeaveDTO leave) {

        return convertToDTO(leaveRepository.save(convertToEntity(leave)));
    }

    public LeaveDTO getLeaveRecordById(Long id) {
        Leave  leave =  leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("Leave not found"));
        return convertToDTO(leave);
    }

    public LeaveDTO updateLeaveRecord(Long id, LeaveDTO leave) {
        leave.setId(id);
        return convertToDTO(leaveRepository.save(convertToEntity(leave)));
    }

    public void deleteLeaveRecord(Long id) {
        leaveRepository.deleteById(id);

    }

    public List<LeaveDTO> getLeaveRecordsByEmployeeId(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LeaveDTO cancelLeaveRequest(Long id) {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new RuntimeException("leave not found"));

        if (leave.getStatus() == LeaveStatus.PENDING || (leave.getStatus() == LeaveStatus.APPROVED && leave.getStartDate().isAfter(leave.getStartDate()))) {
            leaveRepository.deleteById(id);
        }
        else if (leave.getStatus() == LeaveStatus.APPROVED && leave.getEndDate().isAfter(LocalDate.now())) {
            leave.setEndDate(LocalDate.now());
            leaveRepository.save(leave);
        }
        return convertToDTO(leave);

    }

    public LeaveDTO changeLeaveStatus(Long id, LeaveStatus status) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(status);
            leaveRepository.save(leave);
            return convertToDTO(leave);

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

    public List<LeaveDTO> getAllLeaveRecords() {
        return leaveRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<LeaveDTO> searchLeave(Long employeeId, LocalDate startDate, LocalDate endDate, LeaveStatus status) {
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
        }).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
