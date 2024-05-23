package org.example.payroll_management.service;

import org.example.payroll_management.dto.AttendancePolicyDTO;
import org.example.payroll_management.model.AttendancePolicy;
import org.example.payroll_management.repository.AttendancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AttendancePolicyService {
    AttendancePolicyRepository attendancePolicyRepository;



    @Autowired
    public AttendancePolicyService(AttendancePolicyRepository attendancePolicyRepository) {
        this.attendancePolicyRepository = attendancePolicyRepository;
    }

    private AttendancePolicy convertToEntity(AttendancePolicyDTO attendancePolicyDTO) {
        return new AttendancePolicy(
                attendancePolicyDTO.getId(),
                attendancePolicyDTO.getName(),
                attendancePolicyDTO.getDescription(),
                attendancePolicyDTO.getWorkStart(),
                attendancePolicyDTO.getWorkEnd(),
                attendancePolicyDTO.getLateThreshold(),
                attendancePolicyDTO.getEarlyLeaveThreshold(),
                attendancePolicyDTO.getAbsenceThreshold()
        );
    }

    private AttendancePolicyDTO convertToDTO(AttendancePolicy attendancePolicy) {
        return new AttendancePolicyDTO(
                attendancePolicy.getId(),
                attendancePolicy.getName(),
                attendancePolicy.getDescription(),
                attendancePolicy.getWorkStart(),
                attendancePolicy.getWorkEnd(),
                attendancePolicy.getLateThreshold(),
                attendancePolicy.getEarlyLeaveThreshold(),
                attendancePolicy.getAbsenceThreshold()
        );
    }

    public AttendancePolicyDTO createAttendancePolicy(AttendancePolicyDTO attendancePolicyDTO) {
        AttendancePolicy attendancePolicy = convertToEntity(attendancePolicyDTO);
        AttendancePolicy savedAttendancePolicy = attendancePolicyRepository.save(attendancePolicy);
        return convertToDTO(savedAttendancePolicy);
    }
    public AttendancePolicyDTO getAttendancePolicyById(Long id) {
        //TODO: exception
        return convertToDTO(Objects.requireNonNull(attendancePolicyRepository.findById(id).orElse(null)));
    }
    public AttendancePolicyDTO updateAttendancePolicy(Long id, AttendancePolicyDTO attendancePolicyDTO) {

        attendancePolicyDTO.setId(id);

        return convertToDTO(attendancePolicyRepository.save(convertToEntity((attendancePolicyDTO))));
    }
    public void deleteAttendancePolicy(Long id) {
        attendancePolicyRepository.deleteById(id);
    }
}
