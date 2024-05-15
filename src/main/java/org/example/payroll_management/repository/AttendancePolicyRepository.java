package org.example.payroll_management.repository;

import org.example.payroll_management.model.AttendancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendancePolicyRepository extends JpaRepository<AttendancePolicy, Long> {
}
