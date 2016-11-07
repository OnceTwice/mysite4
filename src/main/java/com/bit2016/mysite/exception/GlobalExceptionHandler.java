package com.bit2016.mysite.exception;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(Exception.class)
//	public String handlerException(HttpServletRequest request, Exception e) {
//		System.out.println("exception : " + e);
//		return "error/exception";
//	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, Exception e) {
		// 1. 로깅
		System.out.println("exception : " + e);
		
		
		// 2. ajax 요청 요부 판단
//		if("application/json".equals(request.getContentType())) {
//			
//		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exceptionMessage", e.getMessage());
		mav.setViewName("error/exception");
		return mav;
	}
}
