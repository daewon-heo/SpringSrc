package com.kh.spring.common.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningLoginLogger {
	// 회원 로그인에 성공했을때 어떤 회원이 로그인 하였는지
	// 접속 기록을 남기는 로그 생성 클래스
	
	private Logger logger 
		= LoggerFactory.getLogger(AfterReturningLoginLogger.class);
	
	@Pointcut("execution(* com.kh.spring..MemberController.*Login(..))")
	public void loginPointCut() {}
	
	@AfterReturning(pointcut="loginPointCut()", returning="returnObj")
	public void myAdvice(JoinPoint jp,  Object returnObj) {
		
		// 1. 전달한 데이터를 추출
		ModelAndView mv = (ModelAndView)returnObj;
		
		// 2 . 전달받은 ModelAndView 객체에서 Model객체 추찰
		Map<String, Object> map = mv.getModel();
		
		if(map.containsKey("member")) {
			
			Member m = (Member)map.get("member");
			
			logger.info("[" + m.getUserId() + "] 님이 로그인 하였습니다.");
		}else {
		}
	}
}
