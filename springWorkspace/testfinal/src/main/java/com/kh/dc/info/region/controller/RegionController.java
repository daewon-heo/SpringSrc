package com.kh.dc.info.region.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegionController {
	@RequestMapping("info/region/list.do")
	public String regionList() {
		return "info/region/list";
	}
}
