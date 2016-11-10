package com.bit2016.mysite.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "gallery/form";
	}
}
