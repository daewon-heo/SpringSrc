package com.kh.spring.member.controller;

import java.util.HashMap;

public class HandlerMapping {
 	// 일반 컨트롤러와 사용자가 요청한 url(주소)을
	// 하나로 매칭시키는 역할을 수행한다.
	
	private HashMap<String, Controller> mapping;
	
	public HandlerMapping() {
		mapping = new HashMap<String, Controller>();
		
		mapping.put("/login.do", new LoginController());
	}
	
	public Controller setController(String url) {
		// 특정 url이 오면 해당하는 컨트롤러를 찾아서 돌려주는 메소드
		return mapping.get(url);
	}
}
