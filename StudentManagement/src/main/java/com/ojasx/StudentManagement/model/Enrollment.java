package com.ojasx.StudentManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Students student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Courses course;

    @Column(nullable = false)
    private LocalDate enrolledOn;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @jakarta.persistence.PrePersist
    void onCreate() {
        if (enrolledOn == null) enrolledOn = LocalDate.now();
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Students getStudent() { return student; }
    public void setStudent(Students student) { this.student = student; }
    public Courses getCourse() { return course; }
    public void setCourse(Courses course) { this.course = course; }
    public LocalDate getEnrolledOn() { return enrolledOn; }
    public void setEnrolledOn(LocalDate enrolledOn) { this.enrolledOn = enrolledOn; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
