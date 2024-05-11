package org.example.payroll_management.repository;

import org.example.payroll_management.model.Leave;
import org.example.payroll_management.model.LeaveStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class LeaveRepositoryTest {


    @Autowired
    private LeaveRepository leaveRepository;

    private Leave leave;

    @BeforeEach
    public void setUp() {
        leave = new Leave();
        leave.setStatus(LeaveStatus.APPROVED);
        leave.setStartDate(LocalDate.now().minusDays(1));
        leave.setEndDate(LocalDate.now().plusDays(1));
        leaveRepository.save(leave);

    }

    @Test
    public void testFindApprovedLeavesInEffect() {
        List<Leave> foundLeaves = leaveRepository.findApprovedLeavesInEffect(LocalDate.now());

        assertThat(foundLeaves).containsOnly(leave);
    }

    @Test
    public void testNoApprovedLeavesWhenStatusIsPending() {
        leave.setStatus(LeaveStatus.PENDING);
        leaveRepository.save(leave);

        List<Leave> foundLeaves = leaveRepository.findApprovedLeavesInEffect(LocalDate.now());

        assertThat(foundLeaves).isEmpty();
    }

    @Test
    public void testFindApprovedLeavesNotInEffect() {
        leave.setStartDate(LocalDate.now().plusDays(5));
        leave.setEndDate(LocalDate.now().plusDays(10));
        leave.setStatus(LeaveStatus.APPROVED);
        leaveRepository.save(leave);

        List<Leave> foundLeaves = leaveRepository.findApprovedLeavesInEffect(LocalDate.now());

        assertThat(foundLeaves).isEmpty();
    }



    @AfterEach
    public void tearDown() {
        leaveRepository.deleteAll();
    }


}