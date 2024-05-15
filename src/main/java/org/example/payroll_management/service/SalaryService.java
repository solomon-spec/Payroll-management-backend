package org.example.payroll_management.service;

import org.example.payroll_management.model.Salary;
import org.example.payroll_management.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryService {
    public final SalaryRepository salaryRepository;

    @Autowired
    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public Salary createSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary getSalaryById(Long id) {
        return salaryRepository.findById(id).orElse(null);
    }

    public Salary updateSalary(Long id, Salary salary) {
        salary.setId(id);
        return salaryRepository.save(salary);
    }

    public String deleteSalary(Long id) {
        salaryRepository.deleteById(id);
        return "Salary deleted successfully";
    }

    public List<Salary> getSalariesByEmployeeId(Long employeeId) {
        return salaryRepository.findAllByEmployeeId(employeeId);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
}
