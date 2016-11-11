package com.bit2016.mysite.controller.api;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.dto.*;
import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value="p", required=true, defaultValue="1") Integer page) {
		List<GuestbookVo> list = guestbookService.getMessageList(page);
		
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public JSONResult add(@ModelAttribute GuestbookVo vo) {
		GuestbookVo guestbookVo = guestbookService.writeMessage(vo, true);
		
		return JSONResult.success(guestbookVo);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public JSONResult delete(@ModelAttribute GuestbookVo vo) {
		boolean result = guestbookService.deleteMessage(vo);
		
		return JSONResult.success(result ? vo.getNo() : -1);
	}
}
