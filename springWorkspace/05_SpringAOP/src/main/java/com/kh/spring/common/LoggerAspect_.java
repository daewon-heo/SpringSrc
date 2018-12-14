package com.kh.spring.common;

import org.aspectj.lang.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect_ {
	// 실행되는 메소드의 내용을 출력하는 로그 객체
	// XML을 통한 aspect 등록 방법
	
	private Logger logger = LoggerFactory.getLogger(LoggerAspect_.class);
	
	public Object loggerAdvice(ProceedingJoinPoint jp) throws Throwable {
		logger.debug("선언적 형태의 AOP입니다.");
		
		Signature sig = jp.getSignature();
		
		logger.debug("시그니쳐 내용 : " + sig);
		
		String type = sig.getDeclaringTypeName();
		
		logger.debug("type : " + type);
		
		String methodName = sig.getName();
		
		logger.debug("메소드명 : " + methodName);
		
		String commponentType = "";
		
		if(type.contains("Controller")) {
			
			commponentType = "Controller :: ";
			
		} else if(type.contains("Service")){
			
			commponentType = "Service :: ";
			
		} else if(type.contains("Dao")){
			
			commponentType = "Dao :: ";
			
		}
		
		logger.debug("[Before] " +  commponentType + methodName);
		
		Object obj = jp.proceed();
		
		logger.debug("[After] " +  commponentType + methodName);
		
		return obj;
	}
	

	
}
