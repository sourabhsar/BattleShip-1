package com.demo.exceptionhandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ModelAndView customExceptionHandler(Exception e) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("exception", e.getMessage());
		return mav;
	}
}