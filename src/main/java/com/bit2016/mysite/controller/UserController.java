package com.bit2016.mysite.controller;

import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;
import com.bit2016.security.*;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinForm(@ModelAttribute UserVo userVo) {
		return "/user/joinform";
	}

	@RequestMapping("/loginform")
	public String loginForm() {
		return "/user/loginform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError o : list) {
//				System.out.println("Object Error : " + o);
//			}
			return "user/joinform";
		}
		
		userService.join(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(@AuthUser UserVo authUser, Model model) {
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}
	
	@Auth
	@RequestMapping("/modify")
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo vo) {
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		
		return "redirect:/user/modifyform?update=success";
	}
	
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handlerUserDaoException() {
		// 1. logging(파일에 내용 저장)
		// 2. 사용자에게 안내(error) 페이지
		return "error/500";
	}
	*/
}
