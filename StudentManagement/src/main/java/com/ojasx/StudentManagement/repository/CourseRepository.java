package com.ojasx.StudentManagement.repository;

import com.ojasx.StudentManagement.model.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Long> {

    boolean existsByCourseCodeIgnoreCase(String code);

    Page<Courses > findByActiveTrue(Pageable pageable);
}
