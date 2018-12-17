package com.kh.dc.community.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	@RequestMapping("community/food/list.do")
	public String foodList() {
		return "community/food/list";
	}
}
