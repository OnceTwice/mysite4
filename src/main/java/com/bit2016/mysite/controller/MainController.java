package com.bit2016.mysite.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
}
