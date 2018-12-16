package com.kh.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Advice의 다섯가지 타입
 * 1. Around Advice : 메소드 수행 직전, 직후에 동작하는 공통 소스 코드
 * 2. Before Advice : 메소드 수행 전에 동작하는 Advice 
 * 3. After Advice : 메소드 수행 이후에 동작하는 Advice
 * 		(목표가 되는 메소드의 에러 여부에 관계없이 동작함)
 * 4. After Returning Advice : 메소드가 완료된 이후 동작 
 * 5. After Throwing Advice : 메소드에 예외가 발생했을 경우 동작
 */
public class AdviceSignature {
		private Logger logger = LoggerFactory.getLogger(AdviceSignature.class);
	   
	   @Pointcut("execution(* com.kh.spring..*Impl.*(..))")
	   public void pointcut() {}
	   
	   @Before("pointcut()")
	   public void beforeAdvice(JoinPoint joinPoint) {
	      //타겟메소드 실행전 코드
		   
		  logger.debug("저는 Before Advice 입니다.");
		  
	   }
	   
	   @Around("pointcut()")
	   public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
	      //Target메소드 실행전 코드 
	      
	      Object obj = joinPoint.proceed();
	      
	      //Target메소드 실행후 코드 
	      
	      return obj;
	   }
	   
	   @AfterReturning(pointcut="pointcut()", returning="returnObj")
	   public void afterReturningAdvice(JoinPoint joinPoint, Object returnObj) {
	      //타겟메소드의 리턴 데이터를 다른 용도로 처리할 때 사용함.
	      logger.debug("리턴값  = "+returnObj);
	   }
	   
	   @After("pointcut()")
	   public void afterAdvice() {
	      //타겟메소드의 예외 발생 여부에 상관없이 무조건 수행되는 advice
	   }
	   
	   @AfterThrowing(pointcut="pointcut()", throwing="exceptObj")
	   public void afterThrowingAdvice(JoinPoint jp, Exception exceptObj) {
	      //타겟메소드 실행중 예외가 발생했을 때, 부가적인 로직을 제공할 목적으로 사용하는 Advice
	      String methodName = jp.getSignature().getName();
	      logger.debug(methodName + "() 메소드 수행 중 예외 발생!");
	      
	      if(exceptObj instanceof IllegalArgumentException) {
	         logger.debug("IllegalArgumentException : 부적합한 값이 입력되었습니다.");
	      }else if(exceptObj instanceof NumberFormatException) {
	         logger.debug("NumberFormatException : 숫자 형식의 값이 아닙니다.");
	      }else if(exceptObj instanceof Exception) {
	         logger.debug("Exception : "+exceptObj.getMessage());
	      }else {
	         logger.debug(exceptObj.getMessage());
	      }
	   }
}
