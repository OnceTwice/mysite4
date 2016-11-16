package com.bit2016.mysite.repository;

import java.util.*;

import javax.sql.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2016.mysite.exception.*;
import com.bit2016.mysite.vo.*;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;
	
	// 인증(로그인)
	public UserVo get(String email, String password) throws UserDaoException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}
	
	// 이메일 체크
	public UserVo get( String email ) {
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	// 사용자 가져오기
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert", vo);
	}
	
	public void update( UserVo vo ) {
		sqlSession.update("user.update", vo);
	}	
}
