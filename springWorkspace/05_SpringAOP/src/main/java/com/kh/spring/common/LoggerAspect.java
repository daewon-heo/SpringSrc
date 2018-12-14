package com.kh.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * AOP 등록 시에 XML 형식이 아닌 어노테이션 방식으로 선언할 경우
 * 이를 프로그램 작성 시에 선언을 같이 한다는(런타임 시 등록한다는)
 * 의미에서 프로그래밍적 선언 방식이라고 표현한다.
 * 
 * 어노테이션으로 등록할 경우 해당하는 객체를 스프링 컨테이너에
 * 등록해야 사용이 가능하기 때문에, @Component 어노테이션을 함께
 * 붙여 사용한다.
 */

// @Component
// @Aspect
public class LoggerAspect {
	// 어노테이션을 통한 프로그래밍적 선언 방식
	// Component --> <context:component-scan>
	// Aspect --> <aop:aspectj-autoproxy>
	
	private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	// 포인트 컷 등록
	// 하나의 메소드 형태로 등록하여 해당 메소드를 호출하는 방식으로 연결한다
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void pointcut() {}
	
	//@Around("pointcut()")
	@Around("execution(* com.kh.spring.memo..*(..))")
	public Object loggerAdvice(ProceedingJoinPoint jp) throws Throwable {
		
		logger.debug("프로그래밍적 선언 형태의 AOP입니다.");
		
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

