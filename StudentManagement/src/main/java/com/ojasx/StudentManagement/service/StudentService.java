package com.ojasx.StudentManagement.service;

import com.ojasx.StudentManagement.dto.StudentDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    boolean existsByEmailIgnoreCase(String email);
    StudentDTO createStudent (StudentDTO studentDTO);
    List<StudentDTO> getStudents();
    Page<StudentDTO> getStudents(int page,int size);

    StudentDTO getStudentById(Long id);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
