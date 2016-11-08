package com.bit2016.mysite.controller.api;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Map<String, Object> checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		boolean result = userService.emailExists(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		
		if(result) {
			map.put("data", "exist");
		} else {
			map.put("data", "not exist");
		}
		
		return map;
	}
}
