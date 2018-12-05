package com.kh.spring.di.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.di.xml.vo.Person;

/**
 * Servlet implementation class DependancyXMLServlet
 */
@WebServlet("/xmlDI.do")
public class DependancyXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependancyXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 객체 의존성이란, 객체를 생성할때 new를 통한
		 * 새로운 객체를 생성하는 것 뿐만 아니라,
		 * 다른 곳으로 부터 객체의 정보를 전달 받는 것을 
		 * 의미한다.
		 * 
		 *  1. 생성자를 통한 의존성 주입
		 *  
		 *  	Person p = new Person("홍길동", 직업)
		 *  
		 *  2. Setter를 통한 의존성 주입
		 *  	p.setName("고길동");
		 *  
		 *  3. 다른 메소드를 통한 의존성 주입
		 *  	Person p2 = getPerson
		 *  	Connection con = getConnection();
		 * 
		 */ 
		
		// xml에 저장한 객체의 데이터를 받아오기
		
		ApplicationContext cntx
			= new GenericXmlApplicationContext("/xml-di-context.xml");
		
		// xml정보로 부터 객체의 데이터 추출하기
		
		Person p1 = (Person)cntx.getBean("person1");
		p1.printPerson();
		p1.getMyJob().jobInfo("서울대병원");
		
		Person p2 = (Person)cntx.getBean("person2");
		p2.printPerson();
		p2.getMyJob().jobInfo("자택근무");
		
		System.out.println("====================================");
		
		Person p3 = (Person)cntx.getBean("person1");
		p3.printPerson();
		p3.getMyJob().jobInfo("서울대병원");
		
		Person p4 = (Person)cntx.getBean("person2");
		p4.printPerson();
		p4.getMyJob().jobInfo("자택근무");
		
		System.out.println("p1 == p3 ? " + (p1 == p3));
		System.out.println("p2 == p4 ? " + (p2 == p4));
		
		// 스프링에 객체를 등록하면
		// 싱글톤 패턴으로 객체를 관리한다.
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
