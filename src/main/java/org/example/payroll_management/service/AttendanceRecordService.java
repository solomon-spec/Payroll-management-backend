package org.example.payroll_management.service;

import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.model.AttendanceRecord;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.repository.AttendancePolicyRepository;
import org.example.payroll_management.repository.AttendanceRecordRepository;
import org.example.payroll_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository attendanceRecordRepository, EmployeeRepository employeeRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.employeeRepository = employeeRepository;
    }

    public AttendanceRecord checkInEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setEmployee(employee);
        attendanceRecord.setCheckIn(LocalTime.now());
        attendanceRecordRepository.save(attendanceRecord);
        return attendanceRecord;

    }

    public AttendanceRecord checkOutEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findFirstByEmployeeOrderByCheckInDesc(employee).orElseThrow(() -> new RuntimeException("Employee has not checked in"));
        if (attendanceRecord.getCheckOut() != null) {
            throw new RuntimeException("Employee has already checked out");
        }
        attendanceRecord.setCheckOut(LocalTime.now());
        attendanceRecordRepository.save(attendanceRecord);
        return attendanceRecord;
    }

    public List<AttendanceRecord> getAttendanceRecord(Long employeeId, LocalDate startDate, LocalDate endDate) {
         employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return attendanceRecordRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }

    public List<AttendanceRecord> getAttendanceRecordByTime(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return attendanceRecordRepository.findByDateBetween(startDate, endDate);
    }

    public String getWorkTime(Long employeeId, LocalDate startDate, LocalDate endDate) {
        List<AttendanceRecord> attendanceRecords = getAttendanceRecord(employeeId, startDate, endDate);
        long totalWorkTime = 0;
        for (AttendanceRecord attendanceRecord : attendanceRecords) {
            if (attendanceRecord.getCheckOut() != null) {
                totalWorkTime += attendanceRecord.getCheckOut().toSecondOfDay() - attendanceRecord.getCheckIn().toSecondOfDay();
            }
        }
        long hours = totalWorkTime / 3600;
        long minutes = (totalWorkTime % 3600) / 60;
        long seconds = totalWorkTime % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public List<AttendanceRecord> getLateCheckIns(Long employeeId) {
        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecord> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
            if (attendanceRecord.getCheckOut() == null) {
                return true;
            }
            Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getLateThreshold().getHour())
                    .plusMinutes(attendancePolicy.getLateThreshold().getMinute());

            LocalTime thresholdTime = attendancePolicy.getWorkStart().plus(lateThresholdDuration);

            return attendanceRecord.getCheckIn().isAfter(thresholdTime);

        }).toList();
    }

    public List<AttendanceRecord> getAbsentDays(Long employeeId) {

        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecord> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
                        if (attendanceRecord.getCheckOut() == null) {
                            return true;
                        }
                        Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getAbsenceThreshold().getHour())
                                .plusMinutes(attendancePolicy.getAbsenceThreshold().getMinute());

                        LocalTime thresholdTime = attendancePolicy.getWorkStart().plus(lateThresholdDuration);

                        return attendanceRecord.getCheckIn().isAfter(thresholdTime);

                    }).toList();
    }

    public List<AttendanceRecord> getEarlyLeaves(Long employeeId) {
        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecord> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
            if (attendanceRecord.getCheckOut() == null) {
                return true;
            }
            Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getEarlyLeaveThreshold().getHour())
                    .plusMinutes(attendancePolicy.getWorkStart().getMinute());

            LocalTime thresholdTime = attendancePolicy.getWorkEnd().minus(lateThresholdDuration);

            return attendanceRecord.getCheckIn().isBefore(thresholdTime);

        }).toList();
    }
}