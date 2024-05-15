package org.example.payroll_management.service;

import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.repository.AttendancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendancePolicyService {
    AttendancePolicyRepository attendancePolicyRepository;

    @Autowired
    public AttendancePolicyService(AttendancePolicyRepository attendancePolicyRepository) {
        this.attendancePolicyRepository = attendancePolicyRepository;
    }
    public AttendancePolicy createAttendancePolicy(AttendancePolicy attendancePolicy) {
        return attendancePolicyRepository.save(attendancePolicy);
    }
    public AttendancePolicy getAttendancePolicyById(Long id) {
        return attendancePolicyRepository.findById(id).orElse(null);
    }
    public AttendancePolicy updateAttendancePolicy(Long id, AttendancePolicy attendancePolicy) {
        attendancePolicy.setId(id);
        return attendancePolicyRepository.save(attendancePolicy);
    }
    public String deleteAttendancePolicy(Long id) {
        attendancePolicyRepository.deleteById(id);
        return "Attendance policy deleted successfully";
    }
}
