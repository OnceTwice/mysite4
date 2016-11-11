package com.bit2016.mysite.exception;

import java.io.*;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
	
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, Exception e) {
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		log.error(errors.toString());
		
		// 2. 시스템 오류 화면 안내
		ModelAndView mav = new ModelAndView();
		// mav.addObject("exceptionMessage", e.getMessage());
		mav.setViewName("error/exception");
		return mav;
	}
}
