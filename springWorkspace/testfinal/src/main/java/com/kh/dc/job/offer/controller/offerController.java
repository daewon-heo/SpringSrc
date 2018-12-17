package com.kh.dc.job.offer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class offerController {
	@RequestMapping("job/offer/list.do")
	public String offerList() {
		return "job/offer/list";
	}
}
