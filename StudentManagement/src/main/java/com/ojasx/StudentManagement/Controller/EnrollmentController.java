package com.ojasx.StudentManagement.Controller;

import com.ojasx.StudentManagement.dto.EnrollmentDTO;
import com.ojasx.StudentManagement.repository.CourseRepository;
import com.ojasx.StudentManagement.repository.StudentRepository;
import com.ojasx.StudentManagement.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentController(EnrollmentService enrollmentService, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentService = enrollmentService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<EnrollmentDTO> enrollments = enrollmentService.getEnrollments(Math.max(page, 0), Math.min(Math.max(size, 1), 100));
        model.addAttribute("enrollments", enrollments);
        return "enrollments";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("enrollmentDTO", new EnrollmentDTO());
        addOptions(model);
        return "enrollment-form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("enrollmentDTO") EnrollmentDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (enrollmentService.enrollmentExists(dto.getStudentId(), dto.getCourseId(), null)) result.reject("duplicate", "This student is already enrolled in this course.");
        if (result.hasErrors()) { addOptions(model); return "enrollment-form"; }
        enrollmentService.createEnrollment(dto);
        redirectAttributes.addFlashAttribute("message", "Student enrolled successfully.");
        return "redirect:/enrollments";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("enrollment", enrollmentService.getEnrollmentById(id));
        return "view-enrollment";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("enrollmentDTO", enrollmentService.getEnrollmentById(id));
        addOptions(model);
        return "enrollment-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("enrollmentDTO") EnrollmentDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (enrollmentService.enrollmentExists(dto.getStudentId(), dto.getCourseId(), id)) result.reject("duplicate", "This student is already enrolled in this course.");
        if (result.hasErrors()) { addOptions(model); return "enrollment-form"; }
        enrollmentService.updateEnrollment(id, dto);
        redirectAttributes.addFlashAttribute("message", "Enrollment updated successfully.");
        return "redirect:/enrollments";
    }

    private void addOptions(Model model) {
        model.addAttribute("students", studentRepository.findByActiveTrueOrderByFirstNameAscLastNameAsc());
        model.addAttribute("courses", courseRepository.findByActiveTrueOrderByCourseNameAsc());
    }
}
