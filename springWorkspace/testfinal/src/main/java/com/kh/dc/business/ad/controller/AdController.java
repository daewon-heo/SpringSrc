package com.kh.dc.business.ad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdController {
	@RequestMapping("business/ad/list.do")
	public String adList() {
		return "business/ad/list";
	}
}
