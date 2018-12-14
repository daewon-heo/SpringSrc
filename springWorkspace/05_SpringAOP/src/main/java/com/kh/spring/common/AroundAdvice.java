package com.kh.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	// 각 메소드의 수행 시간 파악하기
	
	private Logger logger 
		= LoggerFactory.getLogger(AroundAdvice.class);
		
	@Pointcut("execution(* com.kh.spring..*Controller.*(..))")
	public void myPointCut() {}
	
	@Around("myPointCut()")
	public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable{
		String methodName = jp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = jp.proceed();
		
		stopWatch.stop();
		
		logger.debug(methodName + "() 소요시간 : " 
				+ stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return obj;
	}
}
