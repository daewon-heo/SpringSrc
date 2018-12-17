package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

public class LoginController implements Controller {

	// 기존의 컨트롤러 인터페이스를 상속받음으로써 
	// 자동으로 request와 response를 사용하는 메소드가 만들어 진다.
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("사용자 로그인 확인");
	
		// 1. 사용자가 입력한 정보 추출
		Member m = new Member();
		m.setUserId(request.getParameter("userId"));
		m.setPassword(request.getParameter("password"));
		
		// 2. 비즈니스 로직 연결
		MemberService ms = new MemberServiceImpl();
		m = ms.selectOne(m);
		
		// 3. 화면 연동
		String page = "";
		
		if( m != null) {
			page = "loginSuccess";
		}else {
			page = "loginFail";
		}
		
		return page;
	}

}
