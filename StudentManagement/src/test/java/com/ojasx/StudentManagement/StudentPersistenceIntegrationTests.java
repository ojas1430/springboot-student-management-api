package com.ojasx.StudentManagement;

import com.ojasx.StudentManagement.dto.StudentDTO;
import com.ojasx.StudentManagement.model.Students;
import com.ojasx.StudentManagement.repository.StudentRepository;
import com.ojasx.StudentManagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StudentPersistenceIntegrationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void createsStudentUsingTheEmailAddressDatabaseColumn() {
        String email = "student-test-" + UUID.randomUUID() + "@example.com";
        StudentDTO student = new StudentDTO();
        student.setFirstName("Test");
        student.setLastName("Student");
        student.setEmail(email);
        student.setPhoneNumber("1234567890");
        student.setAddress("Test address");

        StudentDTO saved = studentService.createStudent(student);

        try {
            assertNotNull(saved.getId());
            Students persisted = studentRepository.findById(saved.getId()).orElseThrow();
            assertEquals(email, persisted.getEmail());
            assertNotNull(persisted.getCreatedAt());
        } finally {
            if (saved.getId() != null) {
                studentRepository.deleteById(saved.getId());
            }
        }
    }
}
