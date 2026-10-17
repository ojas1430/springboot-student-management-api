package com.ojasx.StudentManagement.service;

import com.ojasx.StudentManagement.dto.CourseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    boolean existsByCourseCode(String code);

    Page<CourseDTO> getCourses(int page, int size);

}
