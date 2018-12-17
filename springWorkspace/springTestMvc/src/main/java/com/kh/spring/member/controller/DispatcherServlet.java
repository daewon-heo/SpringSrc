package com.kh.spring.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
       
    @Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자의 요청 url 정보를 추출
		String url = request.getRequestURI();
		// http://localhost:8088/spring/login.do
		// ----> login.do
		String path = url.substring(url.lastIndexOf("/"));
		
		// 2. HandlerMapping으로 path 정보를 보내어 Controller 전달 받기
		Controller ctr = handlerMapping.setController(path);
		
		// 3. 조회한 컨트롤러 실행하기 
		String viewName = ctr.handleRequest(request, response);
		
		// 4. viewResolver를 통해 viewName에 해당하는 화면 조회
		String view = null;
		
		if(!viewName.contains(".do")) {
			// 결과가 일반 페이지일 경우
			view = viewResolver.getView(viewName);
		} else {
			// 결과가 다른 컨트롤러(.do로  끝나는) 요청일 경우
			view = viewName;
		}
		
		// 5. 조회한 화면을 사용자에게 전달
		response.sendRedirect(view);
	}

}
