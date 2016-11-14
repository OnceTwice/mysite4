package com.bit2016.mysite.repository;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import com.bit2016.mysite.vo.*;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Long insert(GuestbookVo vo ) {
		sqlSession.insert("guestbook.insert", vo);
		return vo.getNo();
	}
	
	public int delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo);
	}
	
	public GuestbookVo get(Long no) {
		return sqlSession.selectOne("guestbook.getByNo", no);
	}
	
	public List<GuestbookVo> getList() {
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
//		stopWatch.stop();
//		System.out.println("[ExecutionTime][GuestbookDao.getList] : " + stopWatch.getTotalTimeSeconds() + "millis");
		return sqlSession.selectList("guestbook.getList");
	}
	
	public List<GuestbookVo> getList( Integer page ) {
		return sqlSession.selectList("guestbook.getListByPage", page);
	}	
}
