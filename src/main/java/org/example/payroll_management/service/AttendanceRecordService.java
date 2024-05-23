package org.example.payroll_management.service;

import org.example.payroll_management.dto.AttendanceRecordDTO;
import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.model.AttendanceRecord;
import org.example.payroll_management.model.Employee;
import org.example.payroll_management.repository.AttendanceRecordRepository;
import org.example.payroll_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceRecordService {
    //TODO: update the last 3 function to consider each other and the day that he hasn't checked in or out and the days that he was on leave
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository attendanceRecordRepository, EmployeeRepository employeeRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.employeeRepository = employeeRepository;
    }

    public AttendanceRecordDTO convertToDTO(AttendanceRecord attendanceRecord) {
        return new AttendanceRecordDTO(
                attendanceRecord.getId(),
                attendanceRecord.getEmployee().getId(),
                attendanceRecord.getDate(),
                attendanceRecord.getCheckIn(),
                attendanceRecord.getCheckOut()
        );
    }

    public AttendanceRecord convertToEntity(AttendanceRecordDTO attendanceRecordDTO) {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        Employee employee = employeeRepository.findById(attendanceRecordDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        attendanceRecord.setId(attendanceRecordDTO.getId());
        attendanceRecord.setEmployee(employee);
        attendanceRecord.setDate(attendanceRecordDTO.getDate());
        attendanceRecord.setCheckIn(attendanceRecordDTO.getCheckInTime());
        attendanceRecord.setCheckOut(attendanceRecordDTO.getCheckOutTime());

        return attendanceRecord;
    }

    public AttendanceRecordDTO checkInEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setEmployee(employee);
        attendanceRecord.setCheckIn(LocalTime.now());
        attendanceRecordRepository.save(attendanceRecord);
        return convertToDTO(attendanceRecord);

    }

    public AttendanceRecordDTO checkOutEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findFirstByEmployeeOrderByCheckInDesc(employee).orElseThrow(() -> new RuntimeException("Employee has not checked in"));
        if (attendanceRecord.getCheckOut() != null) {
            throw new RuntimeException("Employee has already checked out");
        }
        attendanceRecord.setCheckOut(LocalTime.now());
        attendanceRecordRepository.save(attendanceRecord);
        return convertToDTO(attendanceRecord);
    }

    public List<AttendanceRecordDTO> getAttendanceRecord(Long employeeId, LocalDate startDate, LocalDate endDate) {
         employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return attendanceRecordRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate).stream().map(this::convertToDTO).toList();
    }

    public List<AttendanceRecordDTO> getAttendanceRecordByTime(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return attendanceRecordRepository.findByDateBetween(startDate, endDate).stream().map(this::convertToDTO).toList();
    }

    public String getWorkTime(Long employeeId, LocalDate startDate, LocalDate endDate) {
        List<AttendanceRecordDTO> attendanceRecords = getAttendanceRecord(employeeId, startDate, endDate);
        long totalWorkTime = 0;
        for (AttendanceRecordDTO attendanceRecord : attendanceRecords) {
            if (attendanceRecord.getCheckOutTime() != null) {
                totalWorkTime += attendanceRecord.getCheckOutTime().toSecondOfDay() - attendanceRecord.getCheckInTime().toSecondOfDay();
            }
        }
        long hours = totalWorkTime / 3600;
        long minutes = (totalWorkTime % 3600) / 60;
        long seconds = totalWorkTime % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public List<AttendanceRecordDTO> getLateCheckIns(Long employeeId) {
        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecordDTO> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
            if (attendanceRecord.getCheckOutTime() == null) {
                return true;
            }
            Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getLateThreshold().getHour())
                    .plusMinutes(attendancePolicy.getLateThreshold().getMinute());

            LocalTime thresholdTime = attendancePolicy.getWorkStart().plus(lateThresholdDuration);

            return attendanceRecord.getCheckInTime().isAfter(thresholdTime);

        }).collect(Collectors.toList());
    }

    public List<AttendanceRecordDTO> getAbsentDays(Long employeeId) {

        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecordDTO> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
                        if (attendanceRecord.getCheckOutTime() == null) {
                            return true;
                        }
                        Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getAbsenceThreshold().getHour())
                                .plusMinutes(attendancePolicy.getAbsenceThreshold().getMinute());

                        LocalTime thresholdTime = attendancePolicy.getWorkStart().plus(lateThresholdDuration);

                        return attendanceRecord.getCheckInTime().isAfter(thresholdTime);

                    }).toList();
    }

    public List<AttendanceRecordDTO> getEarlyLeaves(Long employeeId) {
        AttendancePolicy attendancePolicy  = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found")).getAttendancePolicy();
        List<AttendanceRecordDTO> attendanceRecords = getAttendanceRecord(employeeId, null, null);

        return attendanceRecords.stream().filter(attendanceRecord -> {
            if (attendanceRecord.getCheckOutTime() == null) {
                return true;
            }
            Duration lateThresholdDuration = Duration.ofHours(attendancePolicy.getEarlyLeaveThreshold().getHour())
                    .plusMinutes(attendancePolicy.getWorkStart().getMinute());

            LocalTime thresholdTime = attendancePolicy.getWorkEnd().minus(lateThresholdDuration);

            return attendanceRecord.getCheckInTime().isBefore(thresholdTime);

        }).toList();
    }


}