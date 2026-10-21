package com.ojasx.StudentManagement.Controller;

import com.ojasx.StudentManagement.dto.CourseDTO;
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
        log.info("Get /course/ showing create course page");
        Page<StudentDTO> students = studentService.getStudents(page , size);
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


}
