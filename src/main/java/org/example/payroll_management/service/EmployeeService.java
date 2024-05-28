package org.example.payroll_management.service;

import jakarta.persistence.criteria.Predicate;
import org.example.payroll_management.dto.EmployeeDTO;
import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.Salary;
import org.example.payroll_management.repository.AttendancePolicyRepository;
import org.example.payroll_management.repository.EmployeeRepository;
import org.example.payroll_management.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SalaryRepository salaryRepository;
    private final AttendancePolicyRepository attendancePolicyRepository;

    private final AuthenticationService authenticationService;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           SalaryRepository salaryRepository,
                           AttendancePolicyRepository attendancePolicyRepository,
                           AuthenticationService authenticationService
    ) {
        this.employeeRepository = employeeRepository;
        this.attendancePolicyRepository = attendancePolicyRepository;
        this.salaryRepository = salaryRepository;
        this.authenticationService = authenticationService;
    }

    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setPosition(employeeDTO.getPosition());
        employee.setBankAccount(employeeDTO.getBankAccount());

        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElse(null);
            employee.setManager(manager);
        }

        if (employeeDTO.getCurrentSalaryId() != null) {
            Salary currentSalary = salaryRepository.findById(employeeDTO.getCurrentSalaryId())
                    .orElse(null);
            employee.setCurrentSalary(currentSalary);
        }

        if (employeeDTO.getAttendancePolicyId() != null) {
            AttendancePolicy attendancePolicy = attendancePolicyRepository.findById(employeeDTO.getAttendancePolicyId())
                    .orElse(null);
            employee.setAttendancePolicy(attendancePolicy);
        }

        return employee;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        Long managerId = null;
        if (employee.getManager() != null) {
            managerId = employee.getManager().getId();
        }
//        else {
//            throw new IllegalArgumentException("Manager cannot be null");
//        }

        Long currentSalaryId = null;
        if (employee.getCurrentSalary() != null) {
            currentSalaryId = employee.getCurrentSalary().getId();
        }
//        else {
//            throw new IllegalArgumentException("Current Salary cannot be null");
//        }

        Long attendancePolicyId = null;
        if (employee.getAttendancePolicy() != null) {
            attendancePolicyId = employee.getAttendancePolicy().getId();
        } else {
            throw new IllegalArgumentException("Attendance Policy cannot be null");
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDateOfJoining(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getPosition(),
                employee.getBankAccount(),
                managerId,
                currentSalaryId,
                attendancePolicyId
        );
    }


    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        employee.setId(null);
        System.out.println(convertToEntity(employee));
        authenticationService.signup(employee.getEmail(), "12345678");
        return convertToDTO( employeeRepository.save(convertToEntity(employee)));

    }

    public EmployeeDTO getEmployeeById(Long id) {
        return convertToDTO(employeeRepository.findById(id).orElse(null));
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee) {
        employee.setId(id);
        return convertToDTO(employeeRepository.save(convertToEntity( employee)));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee with id " + id + " does not exist");
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> searchEmployee(String name, String email, String position, String phoneNumber, String dateOfJoiningMin, String dateOfJoiningMax) {
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
        }).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesOnLeave() {
        return employeeRepository.findEmployeesOnLeave().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByManager(Long managerId) {
        return employeeRepository.findByManagerId(managerId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO assignManagerToEmployee(Long employeeId, Long managerId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Employee manager = employeeRepository.findById(managerId).orElse(null);
        if (employee != null && manager != null) {
            employee.setManager(manager);
            return convertToDTO(employeeRepository.save(employee));
        }
        if(employee == null){
            throw new RuntimeException("Employee with id " + employeeId + " does not exist");
        }
        throw new RuntimeException("Employee with id " + managerId + " does not exist");
    }

    public Employee getEmployeeByEmail(String currentUserEmail) {
        return employeeRepository.findByEmail(currentUserEmail).orElse(null);
    }
}
