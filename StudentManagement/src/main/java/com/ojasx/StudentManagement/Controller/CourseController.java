package com.ojasx.StudentManagement.Controller;

import com.ojasx.StudentManagement.dto.CourseDTO;
import com.ojasx.StudentManagement.exception.GlobalExceptionHandler;
import com.ojasx.StudentManagement.service.CourseService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);


    private final CourseService courseService;

    CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/new")
    public String showCreateCourse(Model model){
        log.info("Get /course/new - showing create course page");
        model.addAttribute("courseDTO",new CourseDTO());
        return "add-course";
    }

    @GetMapping("/list")
    public String listCourses(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size ,
                              Model model){
        log.info("Get /course/ showing create course page");
        Page<CourseDTO> courses = courseService.getCourses(page , size);
        model.addAttribute("courses",courses);
        return "courses";
    }

    @PostMapping
    public String createCourse(
            @Valid @ModelAttribute("courseDTO") CourseDTO courseDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        log.info("post /course - create course request received");

        if (bindingResult.hasErrors()) {
            log.error("Post /course - page return due to validation error");
            return "add-course";
        }
        if (courseService.existsByCourseCode(courseDTO.getCourseCode())) {
            log.error("Post /course - Code must be unique");
            bindingResult.rejectValue("courseCode","Code must be unique");
            return "add-course";
        }
        courseService.createCourse(courseDTO);
        redirectAttributes.addAttribute("message","Course created Succssfully");
        log.error("Post /course - course created successfully");
        return "redirect:/course/list";

    }
}
