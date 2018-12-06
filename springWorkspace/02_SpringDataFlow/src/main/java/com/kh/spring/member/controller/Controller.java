package com.kh.spring.member.controller;

import javax.servlet.http.*;

public interface Controller {
	// 다른 컨트롤러의 공통성을 제공하기 위한 인터페이스
	String handleRequest
	(HttpServletRequest request, HttpServletResponse response);
}
