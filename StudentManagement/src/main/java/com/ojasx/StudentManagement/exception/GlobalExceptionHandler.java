package com.ojasx.StudentManagement.exception;

import org.springframework.security.web.csrf.CsrfException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CsrfException.class)
    public String csrfExceptionHandler(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","Session Expired, Please Log in");
        return "redirect  login page";
    }




}
