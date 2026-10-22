package com.ojasx.StudentManagement.service;

import com.ojasx.StudentManagement.dto.EnrollmentDTO;
import org.springframework.data.domain.Page;

public interface EnrollmentService {
    Page<EnrollmentDTO> getEnrollments(int page, int size);
    EnrollmentDTO getEnrollmentById(Long id);
    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);
    boolean enrollmentExists(Long studentId, Long courseId, Long excludeId);
}
