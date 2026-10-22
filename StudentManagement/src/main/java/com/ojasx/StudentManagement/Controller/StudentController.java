package com.ojasx.StudentManagement.Controller;

import com.ojasx.StudentManagement.dto.StudentDTO;
import com.ojasx.StudentManagement.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService1) {

        this.studentService = studentService1;
    }

    @GetMapping("/new")
    public String showCreateStudent(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "add-student";
    }

    @GetMapping("/list")
    public String listStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size ,
            Model model
            ) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), 100);
        log.info("GET /students/list - page {}", safePage);
        Page<StudentDTO> students = studentService.getStudents(safePage, safeSize);
        model.addAttribute("students",students);
        return "students";
    }

    @PostMapping("/save")
    public String createStudent(
            @Valid @ModelAttribute("studentDTO") StudentDTO studentDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "add-student";
        }

        if (studentService.existsByEmailIgnoreCase(studentDTO.getEmail())){
            bindingResult.rejectValue("email", "duplicate", "Email must be unique");
            return "add-student";
        }

        studentService.createStudent(studentDTO);
        redirectAttributes.addAttribute("message","Student is added successfully");
        return "redirect:/students/list";

    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id,Model model){
        StudentDTO student =  studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "view-student";
    }

    @GetMapping("/{id}/edit")
    public String showEditStudent(@PathVariable Long id, Model model) {
        model.addAttribute("studentDTO", studentService.getStudentById(id));
        return "edit-student";
    }

    @PostMapping("/{id}/update")
    public String updateStudent(
            @PathVariable Long id,
            @Valid @ModelAttribute("studentDTO") StudentDTO studentDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "edit-student";
        }
        if (studentService.existsByEmailIgnoreCaseAndIdNot(studentDTO.getEmail(), id)) {
            bindingResult.rejectValue("email", "duplicate", "Email must be unique");
            return "edit-student";
        }

        studentService.updateStudent(id, studentDTO);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        return "redirect:/students/list";
    }


}
