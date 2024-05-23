package org.example.payroll_management.service;

import org.example.payroll_management.dto.SalaryDTO;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.Salary;
import org.example.payroll_management.repository.EmployeeRepository;
import org.example.payroll_management.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryService {
    public final SalaryRepository salaryRepository;
    public final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    private SalaryDTO convertToDTO(Salary salary) {
        return new SalaryDTO(
                salary.getId(),
                salary.getBasicSalary(),
                salary.getHouseAllowance(),
                salary.getTransportAllowance(),
                salary.getMedicalAllowance(),
                salary.getTaxDeduction(),
                salary.getInsuranceDeduction(),
                salary.getEmployee().getId(),
                salary.getBonus()
        );
    }
    private Salary convertToEntity(SalaryDTO salaryDTO) {
        Employee employee = employeeRepository.findById(salaryDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return new Salary(
                salaryDTO.getId(),
                salaryDTO.getBasicSalary(),
                salaryDTO.getHouseAllowance(),
                salaryDTO.getTransportAllowance(),
                salaryDTO.getMedicalAllowance(),
                salaryDTO.getTaxDeduction(),
                salaryDTO.getInsuranceDeduction(),
                employee,
                salaryDTO.getBonus(),
                null
        );
    }


public SalaryDTO createSalary(SalaryDTO salaryDTO) {
    Salary salary = convertToEntity(salaryDTO);
    return convertToDTO(salaryRepository.save(salary));
}

public SalaryDTO getSalaryById(Long id) {
    Salary salary = salaryRepository.findById(id).orElse(null);
    return salary != null ? convertToDTO(salary) : null;
}

public SalaryDTO updateSalary(Long id, SalaryDTO salaryDTO) {
    salaryDTO.setId(id);
    Salary salary = convertToEntity(salaryDTO);
    return convertToDTO(salaryRepository.save(salary));
}

    public void deleteSalary(Long id) {
        salaryRepository.deleteById(id);
    }


    public List<SalaryDTO> getSalariesByEmployeeId(Long employeeId) {
        return salaryRepository.findAllByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public List<SalaryDTO> getAllSalaries() {
        return salaryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
