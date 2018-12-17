package com.kh.dc.info.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HouseController {
	@RequestMapping("info/house/list.do")
	public String houseList() {
		return "info/house/list";
	}
}
