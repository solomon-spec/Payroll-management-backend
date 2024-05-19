package org.example.payroll_management.service;

import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.SalaryPaymentStatus;
import org.example.payroll_management.repository.EmployeeRepository;
import org.example.payroll_management.repository.SalaryPaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryPaymentStatusService {
    private final SalaryPaymentStatusRepository salaryPaymentStatusRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    SalaryPaymentStatusService(SalaryPaymentStatusRepository salaryPaymentStatusRepository, EmployeeRepository employeeRepository){
        this.salaryPaymentStatusRepository = salaryPaymentStatusRepository;
        this.employeeRepository = employeeRepository;
    }


    public List<SalaryPaymentStatus> findByEmployeeId(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found"));

        return salaryPaymentStatusRepository.findBySalaryEmployee(employee);
    }


    public List<SalaryPaymentStatus> findByStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        return salaryPaymentStatusRepository.findByDateOfPaymentBetween(startDate,endDate);
    }

    public List<SalaryPaymentStatus> findByStatus(String status) {
        return salaryPaymentStatusRepository.findByStatus(status);
    }

    public SalaryPaymentStatus save(SalaryPaymentStatus salaryPaymentStatus) {
        return salaryPaymentStatusRepository.save(salaryPaymentStatus);
    }

    public SalaryPaymentStatus update(Long id, SalaryPaymentStatus salaryPaymentStatus) {
        salaryPaymentStatus.setId(id);
        return salaryPaymentStatusRepository.save(salaryPaymentStatus);
    }
}
