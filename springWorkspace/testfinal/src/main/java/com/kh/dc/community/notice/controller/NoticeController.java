package com.kh.dc.community.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping("community/notice/list.do")
	public String noticeList() {
		return "community/notice/list";
	}
}
