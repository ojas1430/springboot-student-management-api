package com.ojasx.StudentManagement.repository;

import com.ojasx.StudentManagement.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Page<Enrollment> findAllByOrderByEnrolledOnDesc(Pageable pageable);
    boolean existsByStudentIdAndCourseIdAndIdNot(Long studentId, Long courseId, Long id);
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
