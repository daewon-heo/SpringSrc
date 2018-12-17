package com.kh.dc.sale.group.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class saleController {
	@RequestMapping("sale/group/list.do")
	public String groupList() {
		return "sale/group/list";
	}
}
