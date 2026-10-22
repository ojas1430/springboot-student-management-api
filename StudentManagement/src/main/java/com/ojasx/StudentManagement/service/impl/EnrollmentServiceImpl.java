package com.ojasx.StudentManagement.service.impl;

import com.ojasx.StudentManagement.dto.EnrollmentDTO;
import com.ojasx.StudentManagement.model.Courses;
import com.ojasx.StudentManagement.model.Enrollment;
import com.ojasx.StudentManagement.model.Students;
import com.ojasx.StudentManagement.repository.CourseRepository;
import com.ojasx.StudentManagement.repository.EnrollmentRepository;
import com.ojasx.StudentManagement.repository.StudentRepository;
import com.ojasx.StudentManagement.service.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<EnrollmentDTO> getEnrollments(int page, int size) {
        return enrollmentRepository.findAllByOrderByEnrolledOnDesc(PageRequest.of(page, size)).map(this::toDto);
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {
        return toDto(findEnrollment(id));
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        apply(dto, enrollment);
        return toDto(enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO dto) {
        Enrollment enrollment = findEnrollment(id);
        apply(dto, enrollment);
        return toDto(enrollmentRepository.save(enrollment));
    }

    @Override
    public boolean enrollmentExists(Long studentId, Long courseId, Long excludeId) {
        return excludeId == null
                ? enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)
                : enrollmentRepository.existsByStudentIdAndCourseIdAndIdNot(studentId, courseId, excludeId);
    }

    private Enrollment findEnrollment(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Enrollment not found."));
    }

    private void apply(EnrollmentDTO dto, Enrollment enrollment) {
        Students student = studentRepository.findById(dto.getStudentId()).orElseThrow(() -> new IllegalArgumentException("Student not found."));
        Courses course = courseRepository.findById(dto.getCourseId()).orElseThrow(() -> new IllegalArgumentException("Course not found."));
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledOn(dto.getEnrolledOn());
        enrollment.setActive(dto.isActive());
    }

    private EnrollmentDTO toDto(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseName(enrollment.getCourse().getCourseName());
        dto.setCourseCode(enrollment.getCourse().getCourseCode());
        dto.setEnrolledOn(enrollment.getEnrolledOn());
        dto.setActive(enrollment.isActive());
        return dto;
    }
}
