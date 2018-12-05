package com.kh.spring.di.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.di.model.vo.BeanFactory;
import com.kh.spring.di.model.vo.LgTV;
import com.kh.spring.di.model.vo.SamsungTV;
import com.kh.spring.di.model.vo.TV;
import com.kh.spring.di.model.vo.TvMgr;

/**
 * Servlet implementation class DependancyServlet
 */
@WebServlet("/dependancy.do")
public class DependancyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependancyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 1. 일반 TV를 서블릿에서 호출하여 사용할 경우
/*			LgTV tv = new LgTV();
			tv.turnOn();
			tv.soundUp();;
			tv.soundDown();
			tv.turnOff();*/
		
			// 삼성 티비로 바꾸기
/*			SamsungTV tv = new SamsungTV();
			tv.powerOn();
			tv.volumnUp();
			tv.volumnDown();
			tv.powerOff();*/
			
			// LgTV에서 SamSungTV로 변경할 때
			// 해당 객체와 관련된 모든 메소드들 까지도
			// 변경해야 하는데, 이러한 영향력을 객체 결합도(의존성)
			// 이라고 하며 위와 같이 거의 모든 내용을 변경할 경우
			// *결합도가 높다라고 표현한다.
			// 결합도가 높은 코드는 해당 객체가 변경됨에 따른 코드 수정이
			// 많아지기 때문에 개발 및 유지보수에 어려움이 생긴다.

			// 이러한 결합도를 낮추기 위해 생성한 인터페이스로
			// 구현하기
//			TV tv = new LgTV();
			/*TV tv = new SamsungTV();
			tv.turnOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.turnOff();*/
			
			// 위의 방식으로 만들게 되면 개발자가 필요로 하는 객체를
			// 계속 new로 생성해주어야 한다.
			// 따라서, 전달받은 값에 따라 해당하는 객체를 생성해주는
			// 전략 디자인 패턴을 적용하여 결합도를 낮춰 줄 수 있다.
			
/*			TV tv = (TV)BeanFactory.getBean(request.getParameter("bean"));
			tv.turnOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.turnOff();*/
		
		// 스프링 컨테이너를 활용한 객체 출력하기(Setter를 활용한 DI 주입 방식)
		AbstractApplicationContext cnxt = 
				new GenericXmlApplicationContext("/sample-context.xml");
		
		TV tv = cnxt.getBean("tvmgr", TvMgr.class).getTv();
		tv.turnOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.turnOff();
		
		// 이렇게 xml 문서를 읽게 함으로써
		// 객체에 대한 결합도 / 의존성을 xml파일에게 양도하게 되었다.
		// 그래서 개발자가 더이상 직접 new를 통한 객체 생성을
		// 할 필요가 없어졌다.
		// 스프링 컨테이너는 자체적으로 객체의 생명 주기를 관리하며
		// 더이상 개발자 중심의 코드가 아닌 스프링 프레임워크가 중심이
		// 제어의 역행(IoC) 기법을 구현하게 되었다.
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
