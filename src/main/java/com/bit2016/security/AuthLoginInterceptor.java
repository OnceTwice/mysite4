package com.bit2016.security;

import javax.servlet.http.*;

import org.springframework.context.*;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.handler.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler/*HandlerMethod*/) throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Web Application Context 받아오기 
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		// Container 안에 있는 UserService Bean(객체) 받아오기
		UserService userService = ac.getBean(UserService.class);
		
		// 데이터베이스에서 해당 UserVo 받아오기
		UserVo userVo = userService.login(email, password);
		
		// 이메일과 패스워드가 일치하지 않는 경우
		if(userVo == null) {
			response.sendRedirect(request.getContextPath() + "/user/loginform?result=fail");
			return false;
		}
		
		// 인증처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
