package com.kh.dc.sale.used.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class usedController {
	@RequestMapping("sale/used/list.do")
	public String usedList() {
		return "sale/used/list";
	}
}
