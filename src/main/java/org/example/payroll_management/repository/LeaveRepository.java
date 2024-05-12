package org.example.payroll_management.repository;

import org.example.payroll_management.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>,   JpaSpecificationExecutor<Leave>{
    List<Leave> findByEmployeeId(Long employeeId);
    @Query("SELECT l FROM Leave l WHERE l.status = 'APPROVED' AND :currentDate BETWEEN l.startDate AND l.endDate")
    List<Leave> findApprovedLeavesInEffect(LocalDate currentDate);

}
