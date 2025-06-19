package com.proc.system.Controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    public GlobalErrorHandler() {


    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(Model model,Exception ex){

        model.addAttribute("errorName","Operation Failed");
        model.addAttribute("errorDescription",ex.getMessage());

        return "errorPage";
    }

}
