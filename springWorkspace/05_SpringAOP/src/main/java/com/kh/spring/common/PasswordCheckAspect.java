package com.kh.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kh.spring.memo.model.exception.MemoException;

@Component
@Aspect
public class PasswordCheckAspect {
	
	private Logger logger 
		= LoggerFactory.getLogger(PasswordCheckAspect.class);
	
	@Before("execution(* com.kh.spring..*Controller.insert*(..))"
			+ " or execution(* com.kh.spring..*Controller.delete*(..))")
	public void passwordCheck(JoinPoint jp) {
		Object[] args = jp.getArgs();
		
		String password = String.valueOf(args[1]);
		
		if(password.length() < 4)
			throw new MemoException("비밀번호는 4자리 이상이어야 합니다.");
	}
	
}
