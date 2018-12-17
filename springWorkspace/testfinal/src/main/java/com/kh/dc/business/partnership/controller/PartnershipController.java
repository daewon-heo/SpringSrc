package com.kh.dc.business.partnership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PartnershipController {
	@RequestMapping("business/partnership/list.do")
	public String partnershipList() {
		return "business/partnership/list";
	}
}
