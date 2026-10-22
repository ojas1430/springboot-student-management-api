package com.ojasx.StudentManagement.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class EnrollmentDTO {
    private Long id;
    @NotNull(message = "Select a student.") private Long studentId;
    @NotNull(message = "Select a course.") private Long courseId;
    @NotNull(message = "Enrollment date is required.") private LocalDate enrolledOn = LocalDate.now();
    private boolean active = true;
    private String studentName;
    private String courseName;
    private String courseCode;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public LocalDate getEnrolledOn() { return enrolledOn; }
    public void setEnrolledOn(LocalDate enrolledOn) { this.enrolledOn = enrolledOn; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
}
