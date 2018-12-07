package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller
public class MemberController {
	// 스프링 에서는 MVC 설계 패턴을 지원하기 위한
	// 어노테이션을 별도로 제공한다.
	// @Component() ---> @Controller (Controller용) 
	//				---> @Service	 (Service용)
	//				---> @Repository (Dao 용)
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login.do")
	public String memberLoginCheck(
			@RequestParam String userId,
			@RequestParam String password) {
		// 1. httpServletRequest request, HttpServletResponse response
		// 2. Member 객체 하나로 받기 (CommandMap)
		// 3. @RequestParam
		
		System.out.println("로그인 버튼 클릭 확인, 로그인 서비스로 연결합니다.");
		System.out.println("MemberService : " + memberService);
		
		Member m = new Member();
		
		m.setUserId(userId);
		m.setPassword(password);
		
		m = memberService.selectOne(m);
		
		System.out.println("조회한 회원 정보 : " + m);
		
		String page = null;
		if(m!= null) {
			page = "loginSuccess";
		}else {
			page = "loginFail";
		}
		
		return page;
	}

}
