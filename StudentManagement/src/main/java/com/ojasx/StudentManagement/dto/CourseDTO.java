package com.ojasx.StudentManagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CourseDTO {

    private Long id;


    @NotBlank(message = "Course Name is required.")
    @Size(max = 150,message = "Max of 150 characters allowed.")
    private String courseName;

    @NotBlank(message = "Course Code is required.")
    public String getCourseCode;

    private String duration;

    @NotNull(message = "Course Fee is required.")
    private BigDecimal fee;

    @Size(max = 500,message = "Max of 500 characters allowed.")
    private String description;

    private LocalDateTime createdAt;


    public @NotNull(message = "Course Code is required.") String getCourseCode() {
        return getCourseCode;
    }

    public void setCourseCode(@NotNull(message = "Course Code is required.") String getCourseCode) {
        this.getCourseCode = getCourseCode;
    }

    public @NotNull(message = "Course Name is required.") @Size(max = 150, message = "Max of 150 characters allowed.") String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NotNull(message = "Course Name is required.") @Size(max = 150, message = "Max of 150 characters allowed.") String courseName) {
        this.courseName = courseName;
    }

    public @NotNull(message = "Course Code is required.") String getGetCourseCode() {
        return getCourseCode;
    }

    public void setGetCourseCode(@NotNull(message = "Course Code is required.") String getCourseCode) {
        this.getCourseCode = getCourseCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public @NotNull(message = "Course Fee is required.") BigDecimal getFee() {
        return fee;
    }

    public void setFee(@NotNull(message = "Course Fee is required.") BigDecimal fee) {
        this.fee = fee;
    }

    public @Size(max = 500, message = "Max of 500 characters allowed.") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Max of 500 characters allowed.") String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
