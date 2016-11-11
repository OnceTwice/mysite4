package com.bit2016.mysite.controller.api;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.dto.*;
import com.bit2016.mysite.service.*;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		SingletonClass sc = SingletonClass.getInstance();
		boolean result = userService.emailExists(email);
		
		return JSONResult.success(result ? "exist" : "not exist");
	}
}
