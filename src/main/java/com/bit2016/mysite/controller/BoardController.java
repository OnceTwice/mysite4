package com.bit2016.mysite.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;
import com.bit2016.security.*;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "" )
	public String index(@RequestParam( value="p", required=true, defaultValue="1") Integer page,
						@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
						Model model ) {
		
		Map<String, Object> map = boardService.getMessageList(page, keyword);
		
		model.addAttribute( "map", map );
		return "board/index";
	}

	@RequestMapping( value="/write", method=RequestMethod.GET )
	public String write() {
		return "board/write";
	}
	
	@Auth()
	@RequestMapping( value="/write", method=RequestMethod.POST )
	public String write( HttpSession session, @ModelAttribute BoardVo vo ) {
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		// 권한 체크
		if( authUser == null ){
			return "redirect:/user/loginform";
		}
			
		vo.setUserNo( authUser.getNo() );
		boardService.writeMessage(vo);
		return "redirect:/board";
	}
	
}
