package com.bit2016.mysite.repository;

import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.bit2016.mysite.vo.*;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(GalleryVo vo) {
		
	}
	
	public void delete(GalleryVo vo) {
		
	}
	
	public List<GalleryVo> getList() {
		return sqlSession.selectList("gallery.getList");
	}
}
