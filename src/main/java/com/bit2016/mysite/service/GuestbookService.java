package com.bit2016.mysite.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2016.mysite.repository.*;
import com.bit2016.mysite.vo.*;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
//	public GuestbookVo get(Long no) {
//		return guestbookDao.get(no);
//	}
//	
//	public void insert(GuestbookVo vo) {
//		guestbookDao.insert(vo);
//	}
	
	public boolean deleteMessage(GuestbookVo vo) {
		int count = guestbookDao.delete(vo);
		return count == 1;
	}
	
	public List<GuestbookVo> getMessageList() {
		return guestbookDao.getList();
	}
	
	public List<GuestbookVo> getMessageList(int page) {
		return guestbookDao.getList(page);
	}
	
	public GuestbookVo writeMessage(GuestbookVo vo, boolean fetch) {
		GuestbookVo guestbookVo = null;
		Long no = guestbookDao.insert(vo);
		if(fetch) {
			guestbookVo = guestbookDao.get(no);
		}
		
		return guestbookVo;
	}
	
//	public GuestbookVo writeMessage2(GuestbookVo vo) {
//		Long no = guestbookDao.insert(vo);
//		System.out.println(no);
//		return null;
//	}
	
}
