package com.bit2016.mysite.controller;

import java.util.*;

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
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookService.writeMessage(vo, false);
		
		return "redirect:/guestbook";
	}
	
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookService.deleteMessage(vo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping("")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping("/ajax")
	public String ajax() {
		return "guestbook/index-ajax";
	}
}
