package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	// Controller 실행 시에 전, 후 시점을 캐치하여
	// 해당 시점에서 동작할 코드를 미리 선언하여 작동 시키는 클래스
	// - 시점 별 구현 메소드 - 
	//	1. preHandle(전) : controller 수행 이전에 동작하는 메소드
	//	2. postHandle(후) : controller 수행 이후에 동작하는 메소드
	//	3. afterCompletion(완료 시점) : controller가 완전히 끝난 이후에 동작할 메소드

	private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("==================== START ====================");
			logger.debug(request.getRequestURI());
			logger.debug("-----------------------------------------------");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("------------------- VIEW ----------------------");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("=================== END =====================\n");
		super.afterCompletion(request, response, handler, ex);
	}
	
	
}
