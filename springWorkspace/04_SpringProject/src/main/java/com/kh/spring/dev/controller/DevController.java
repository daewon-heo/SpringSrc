package com.kh.spring.dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.dev.model.service.DevService;
import com.kh.spring.dev.model.vo.Dev;


@Controller
public class DevController {
	@Autowired
	DevService devService;
	
	@RequestMapping("demo/demo.do")
	public String demo1() {
		System.out.println("/demo/demo.do가 실행됩니다~");
		
		return "demo/demo";
	}
	
	@RequestMapping("demo/demo1.do")
	public String demo1(HttpServletRequest req) {
		
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devEmail = req.getParameter("devEmail");
		String devGender = req.getParameter("devGender");
		String[] devLang = req.getParameterValues("devLang");
		
		Dev dev = new Dev(devName, devAge, devEmail, devGender, devLang);
		
		System.out.println("dev : " + dev);
		
		req.setAttribute("dev", dev);
		
		return "demo/demoResult";
		
	}
	
	@RequestMapping("demo/demo2.do")
	public String demo2(HttpServletRequest req, 
			@RequestParam(value="devName") String devName,
			@RequestParam(value="devAge") int devAge,
			@RequestParam(value="devEmail") String devEmail,
			@RequestParam(value="devGender") String devGender,
			@RequestParam(value="devLang") String[] devLang) {
		
		/* RequestParam을 사용하면 기본적으로 required = true 속성이 주어지기 떄문에,
		 * 만약 필수사항이 아닌 변수를 받을 때에는 required = false 속성을 붙여 주어야 한다.
		 * 그렇지 않으면 전송된 값이 없거나 형변환시 에러가 발생할 경우 400 Bad Request 에러가 발생한다. */
		
		Dev dev = new Dev(devName, devAge, devEmail, devGender, devLang);
		
		req.setAttribute("dev", dev);
		
		return "demo/demoResult";
	}
	
	@RequestMapping("demo/demo3.do")
	public String demo3(Dev dev, Model model) {
		// CommandMap : 요청 url로부터 값을 받아올 때, 특정 객체의 내용을 통째로 받아오고 싶다면
		//				파라미터의 이름을 해당 객체의 필드와 동일하게 지어주면 스프링의 커맨맵이라는
		//				별도의 맵핑 방식에 의해 해당 객체를 생성하며 그 값을 넣은 채로 사용할 수 있다.
		// Model / ModelAndView : 
		// Model - request, response와 같은 전달에 필요한 내용들을 각각의 요청, 응답 객체가 아닌
		//			하나의 객체로 처리하기 위한 스프링 객체
		// ModelAndView - request, response 뿐만 아니라 선정하고자 하는 view의 이름을 
		//				   함께 지정하여 응답에 사용하는 객체
		
		System.out.println("demo3.do : " + dev);
		
		// 커맨드맵 객체는 Model 객체에 미리 등록이 되어 있다.
		// model.addAttribute();
		
		return "demo/demoResult";
	}
	
	@RequestMapping(value="demo/insertDev.do", method= RequestMethod.POST)
	public String insertDev(Dev dev) {
		
		System.out.println("DEV : " +  dev);
		
		int result = devService.insertDev(dev);
		
		System.out.println("insert 결과 : " + result);
		
		return "redirect:/";
	}
	
	@RequestMapping("demo/selectDevList.do")
	public String selectList(Model model) {
		List<Dev> list = devService.selectList();
		System.out.println(list);
		
		model.addAttribute("list",list);
		
		return "demo/devList";
	}
	
	@RequestMapping("demo/updateDev.do")
	public String UpdateDev(Model model, @RequestParam int no) {
		model.addAttribute("dev", devService.selectOne(no));
		
		return "demo/devForm";
	}
	
	@RequestMapping("demo/updateDevEnd.do")
	public String updateDevEnd(Dev dev) {
		devService.updateDev(dev);
		
		return "redirect:/demo/selectDevList.do";
	}
	
	@RequestMapping("demo/deleteDev.do")
	public String deleteDev(@RequestParam int no) {
		devService.deleteDev(no);
		
		return "redirect:/demo/selectDevList.do";
	}
	
}
