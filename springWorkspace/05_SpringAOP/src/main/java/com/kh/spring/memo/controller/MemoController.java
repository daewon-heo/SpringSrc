package com.kh.spring.memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.memo.model.service.MemoService;

@Controller
public class MemoController {
    
    private Logger logger = LoggerFactory.getLogger(MemoController.class);
    
    @Autowired
	MemoService memoService;

    @RequestMapping("/memo/memo.do")
	public String memo(Model model){
		logger.debug("메모페이지 요청");
		ModelAndView mav = new ModelAndView();

		List<Map<String,String>> memoList = memoService.selectMemoList();
		model.addAttribute("memoList",memoList);

		return "memo/memo";
	}

    @RequestMapping("/memo/insertMemo.do")
	public String insertMemo(@RequestParam String memo, @RequestParam String password){
		logger.debug("메모 저장");
		
		Map<String, String> map = new HashMap<>();
		
		map.put("memo", memo);
		map.put("password", password);
		memoService.insertMemo(map);
		
		return "redirect:/memo/memo.do";
		
	}
    
    @RequestMapping("/memo/deleteMemo.do")
	public String deleteMemo(@RequestParam String no, @RequestParam String password){
		logger.debug("메모 삭제");
		Map<String, String> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		memoService.deleteMemo(map);
		
		return "redirect:/memo/memo.do";
	}
}
