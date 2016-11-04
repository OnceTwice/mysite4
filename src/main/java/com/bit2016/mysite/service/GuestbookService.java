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
	
	public GuestbookVo get(Long no) {
		return guestbookDao.get(no);
	}
	
	public void insert(GuestbookVo vo) {
		guestbookDao.insert(vo);
	}
	
	public void delete(GuestbookVo vo) {
		guestbookDao.delete(vo);
	}
	
	public List<GuestbookVo> list() {
		return guestbookDao.getList();
	}
}
