package org.example.payroll_management.repository;

import org.example.payroll_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
   @Query("SELECT e FROM Employee e JOIN e.leaves l WHERE l.status = 'APPROVED' AND CURRENT_DATE BETWEEN l.startDate AND l.endDate")
    List<Employee> findEmployeesOnLeave();

    List<Employee> findByManagerId(Long managerId);

    Optional<Employee> findByEmail(String currentUserEmail);
}
