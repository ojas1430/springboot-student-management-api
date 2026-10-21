package com.ojasx.StudentManagement.repository;

import com.ojasx.StudentManagement.model.Courses;
import com.ojasx.StudentManagement.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students,Long> {

    boolean existsByEmailIgnoreCase(String email);

    Page<Students> findByActiveTrue(Pageable pageable);

}
