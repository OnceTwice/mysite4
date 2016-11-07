package com.bit2016.mysite.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2016.mysite.repository.*;
import com.bit2016.mysite.vo.*;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	public UserVo login(String email, String password) {
		UserVo userVo = null;;		// local 변수 초기화
		userVo = userDao.get(email, password);
		return userVo;
	}
	
	public UserVo getUser(Long no) {
		return userDao.get(no);
	}
	
	public void updateUser(UserVo vo) {
		userDao.update(vo);
	}
}
