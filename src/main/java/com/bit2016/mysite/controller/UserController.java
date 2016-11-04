package com.bit2016.mysite.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinForm() {
		return "/user/joinform";
	}

	@RequestMapping("/loginform")
	public String loginForm() {
		return "/user/loginform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		//System.out.println(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="email", required=true, defaultValue="") String email,
						@RequestParam(value="password", required=true, defaultValue="") String password,
						HttpSession session) {
		UserVo userVo = userService.login(email, password);
		
		if(userVo == null) {
			return "redirect:/user/loginform?result=fail";
		}
		
		// 인증 성공
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		// 접근제한
		if(authUser == null) {
			return "redirect:/user/loginform?redirect-url=/user/modifyform";
		}
		
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		// 접근제한
		if(authUser == null) {
			return "redirect:/user/loginform";
		}
		
		System.out.println(authUser);
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		
		return "redirect:/user/modifyform?update=success";
	}
}
