package com.bit2016.mysite.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	@RequestMapping("")
	public String index() {
		System.out.println("안녕");
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("!!!!!");
		return "안녕 jRevel";
	}
}
