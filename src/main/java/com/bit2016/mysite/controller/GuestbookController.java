package com.bit2016.mysite.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookService.insert(vo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") int no, Model model) {
		model.addAttribute("no", no);
		
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println("전 : " + vo);
		guestbookService.delete(vo);
		System.out.println("후 : " + vo);
		
		return "redirect:/guestbook/";
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list", guestbookService.list());
		return "/guestbook/list";
	}
}
