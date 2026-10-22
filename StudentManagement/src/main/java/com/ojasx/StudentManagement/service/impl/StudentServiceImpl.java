package com.ojasx.StudentManagement.service.impl;

import com.ojasx.StudentManagement.dto.StudentDTO;
import com.ojasx.StudentManagement.model.Students;
import com.ojasx.StudentManagement.repository.StudentRepository;
import com.ojasx.StudentManagement.service.StudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger log =
            LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }


    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return studentRepository.existsByEmailIgnoreCase(email);

    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Students students = mapper.map(studentDTO,Students.class);
        Students saved = studentRepository.save(students);
        return mapper.map(saved,StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getStudents() {
        return studentRepository.findAll().stream()
                .map(student -> mapper.map(student, StudentDTO.class))
                .toList();
    }

    @Override
    public Page<StudentDTO> getStudents(int page, int size) {
        log.info("list of students from: {}", page);

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "id")
        );

        return studentRepository.findByActiveTrue(pageRequest)
                .map(student -> mapper.map(student, StudentDTO.class));
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student Found"));
        return mapper.map(students,StudentDTO.class);
    }

    @Override
    public boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id) {
        return studentRepository.existsByEmailIgnoreCaseAndIdNot(email, id);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Students student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student Found"));

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setAddress(studentDTO.getAddress());
        student.setActive(studentDTO.isActive());

        return mapper.map(studentRepository.save(student), StudentDTO.class);
    }


}
