package com.bit2016.mysite.controller;

import org.apache.commons.logging.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	private static final Log log = LogFactory.getLog(MainController.class);
	
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		log.debug("/MainController.hello() called...");
		return "안녕 jRevel";
	}
}
