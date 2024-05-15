package org.example.payroll_management.repository;

import org.example.payroll_management.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository  extends JpaRepository<Salary, Long>{

    List<Salary> findAllByEmployeeId(Long employeeId);

}
