package com.kh.spring.member.controller;

public class ViewResolver {
	// 서비스 로직 처리 후 컨트롤러에서
	// 프론트 컨트롤러를 통해 사용자에게
	// 전달할 때 활용할 View 경로 생성 클래스
	
	public String prefix;
	public String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;	// /WEB-INF/views
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;	// .jsp / .html
	}
	
	public String getView(String viewName) {
		// Ex) viewName : loginSuccess
		// --> /WEB-INF/views/loginSuccess.jsp
		
		return prefix + viewName + suffix;
	}
}
