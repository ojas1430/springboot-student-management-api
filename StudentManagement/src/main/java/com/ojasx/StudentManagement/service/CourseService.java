package com.ojasx.StudentManagement.service;

import com.ojasx.StudentManagement.dto.CourseDTO;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    boolean existsByCourseCode(String code);
}
