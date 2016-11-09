package com.bit2016.security;

import javax.servlet.http.*;

import org.springframework.core.*;
import org.springframework.web.bind.support.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

import com.bit2016.mysite.vo.*;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		// @AuthUser 가 안붙어 있음
		if(authUser == null) {
			return false;
		}
		
		// 파라미터 타입이 UserVo가 아님
		if(parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}
		
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		// @AuthUser 붙어 있고
		// 파라미터 타입이 UserVo
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return session.getAttribute("authUser");
	}

}
