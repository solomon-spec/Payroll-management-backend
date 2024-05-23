package org.example.payroll_management.service;

import org.example.payroll_management.dto.SalaryPaymentStatusDTO;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.Salary;
import org.example.payroll_management.model.SalaryPaymentStatus;
import org.example.payroll_management.repository.EmployeeRepository;
import org.example.payroll_management.repository.SalaryPaymentStatusRepository;
import org.example.payroll_management.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryPaymentStatusService {
    private final SalaryPaymentStatusRepository salaryPaymentStatusRepository;
    private final EmployeeRepository employeeRepository;
    private final SalaryRepository salaryRepository;

    @Autowired
    SalaryPaymentStatusService(SalaryPaymentStatusRepository salaryPaymentStatusRepository, EmployeeRepository employeeRepository, SalaryRepository salaryRepository){
        this.salaryPaymentStatusRepository = salaryPaymentStatusRepository;
        this.employeeRepository = employeeRepository;
        this.salaryRepository  = salaryRepository;
    }
    public SalaryPaymentStatus convertToEntity(SalaryPaymentStatusDTO salaryPaymentStatusDTO) {
        Salary salary = salaryRepository.findById(salaryPaymentStatusDTO.getSalaryId())
                .orElseThrow(() -> new RuntimeException("Salary not found"));

        return new SalaryPaymentStatus(
                salaryPaymentStatusDTO.getId(),
                salaryPaymentStatusDTO.getStatus(),
                salaryPaymentStatusDTO.getDateOfPayment(),
                salary
        );
    }

    public SalaryPaymentStatusDTO convertToDTO(SalaryPaymentStatus salaryPaymentStatus) {
        return new SalaryPaymentStatusDTO(
                salaryPaymentStatus.getId(),
                salaryPaymentStatus.getStatus(),
                salaryPaymentStatus.getDateOfPayment(),
                salaryPaymentStatus.getSalary().getId()
        );
    }

    public List<SalaryPaymentStatusDTO> findByEmployeeId(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found"));

        return salaryPaymentStatusRepository.findBySalaryEmployee(employee).stream().map(this::convertToDTO).toList();
    }


    public List<SalaryPaymentStatusDTO> findByStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        return salaryPaymentStatusRepository.findByDateOfPaymentBetween(startDate,endDate).stream().map(this::convertToDTO).toList();
    }

    public List<SalaryPaymentStatusDTO> findByStatus(String status) {
        return salaryPaymentStatusRepository.findByStatus(status).stream().map(this::convertToDTO).toList();
    }

    public SalaryPaymentStatusDTO save(SalaryPaymentStatusDTO salaryPaymentStatusDTO) {
        SalaryPaymentStatus salaryPaymentStatus = convertToEntity(salaryPaymentStatusDTO);
        return convertToDTO(salaryPaymentStatusRepository.save(salaryPaymentStatus));
    }

    public SalaryPaymentStatusDTO update(Long id, SalaryPaymentStatusDTO salaryPaymentStatusDTO) {
        salaryPaymentStatusDTO.setId(id);
        SalaryPaymentStatus salaryPaymentStatus = convertToEntity(salaryPaymentStatusDTO);
        return convertToDTO(salaryPaymentStatusRepository.save(salaryPaymentStatus));
    }
}
