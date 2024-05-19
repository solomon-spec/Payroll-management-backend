package org.example.payroll_management.repository;

import org.example.payroll_management.model.Employee;
import org.example.payroll_management.model.SalaryPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryPaymentStatusRepository extends JpaRepository<SalaryPaymentStatus, Long> {

    List<SalaryPaymentStatus> findBySalaryEmployee(Employee employee);

    List<SalaryPaymentStatus> findByDateOfPaymentBetween(LocalDate start, LocalDate end);


    List<SalaryPaymentStatus> findByStatus(String status);
}
