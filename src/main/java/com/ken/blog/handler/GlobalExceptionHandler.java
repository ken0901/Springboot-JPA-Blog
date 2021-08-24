package com.ken.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException ie){
        return "<h1>"+ie.getMessage()+"</h1>";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        return "<h1>"+e.getMessage()+"</h1>";
    }
}
