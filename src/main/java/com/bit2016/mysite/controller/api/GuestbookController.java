package com.bit2016.mysite.controller.api;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bit2016.mysite.service.*;
import com.bit2016.mysite.vo.*;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value="p", required=true, defaultValue="1") Integer page) {
		List<GuestbookVo> list = guestbookService.getMessageList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Map<String, Object> add(@ModelAttribute GuestbookVo vo) {
		GuestbookVo guestbookVo = guestbookService.writeMessage(vo, true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", guestbookVo);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public Map<String, Object> delete(@ModelAttribute GuestbookVo vo) {
		boolean result = guestbookService.deleteMessage(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", result ? vo.getNo() : -1);
		
		return map;
	}
}
