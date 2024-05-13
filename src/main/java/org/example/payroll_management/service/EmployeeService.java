package org.example.payroll_management.service;

import jakarta.persistence.criteria.Predicate;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployee(String name, String email, String position, String phoneNumber, String dateOfJoiningMin, String dateOfJoiningMax) {
        return employeeRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new java.util.ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + name + "%"));
            }
            if (email != null) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }
            if (position != null) {
                predicates.add(criteriaBuilder.like(root.get("position"), "%" + position + "%"));
            }
            if (phoneNumber != null) {
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
            }
            if (dateOfJoiningMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfJoining"), java.time.LocalDate.parse(dateOfJoiningMin)));
            }
            if (dateOfJoiningMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateOfJoining"), java.time.LocalDate.parse(dateOfJoiningMax)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Employee> getEmployeesOnLeave() {
        return employeeRepository.findEmployeesOnLeave();
    }

    public List<Employee> getEmployeesByManager(Long managerId) {
        return employeeRepository.findByManagerId(managerId);
    }

    public Employee assignManagerToEmployee(Long employeeId, Long managerId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Employee manager = employeeRepository.findById(managerId).orElse(null);
        if (employee != null && manager != null) {
            employee.setManager(manager);
            return employeeRepository.save(employee);
        }
        return null;
    }
}
