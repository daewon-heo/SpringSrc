package com.kh.dc.job.hunt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class huntController {
	@RequestMapping("job/hunt/list.do")
	public String huntList() {
		return "job/hunt/list";
	}
}
