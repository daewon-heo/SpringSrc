package com.kh.dc.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.dc.test.model.service.TestService;
import com.kh.dc.test.model.vo.TestDc;

@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("test/test.do")
	public void test1() {
		System.out.println("test1() 실행");
	}
	
	@RequestMapping("test/select.do")
	public String testSelect() {
		
		System.out.println("test/select.do > testSelect() 실행");
		
		
		TestDc test = testService.selectOne();
		
		System.out.println(test);
		
		return "test/test";
	}
}
