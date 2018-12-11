package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
//	@Autowired
//	private ShaPasswordEncoder shaPasswordEncoder;
	
	// 비밀번호 암호화 객체
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		
		return "member/memberEnroll";
		
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberEnrollEnd(Member member, Model model) {
		
		// 원래 비밀번호
		String rawPassword = member.getPassword();
		System.out.println("비밀번호 암호화 전 : " + rawPassword);
		
		// ***** 암호화 코드 **** //
		// BcryptPasswordEncoder - 랜덤 salt 생성하는 암호 모듈
		
		member.setPassword(bcryptPasswordEncoder.encode(rawPassword));
		// $2a$10$miYhc3aQsmaacH/CSg4cYekA4bEnMschins40/kh/o4k5Ucim25Cy
		// $2a$10$ : 암호화에 사용된 알고리즘
		// 
		// ****************** //
		
		System.out.println("비밀번호 암호화 후 : " + member.getPassword());
		
		String loc = "/";
		String msg = "";
		
		int result = memberService.insertMember(member);
		
		if(result > 0 ) msg = "회원가입 성공!";
		else msg = "회원 가입 실패";
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
	}
	
	// Httpsession 객체를 직접 사용하는 방법
/*	@RequestMapping(value="/member/memberLogin.do", 
			method= RequestMethod.POST)
	public String memberLogin(@RequestParam String userId,
								@RequestParam String password,
								HttpSession session,
								Model model) throws MemberException {
		try {
			
			Member m = memberService.selectOne(userId);
			
			String loc = "/";
			String msg = "";
			
			if(m == null) {
				msg = "존재하지 않은 아이디입니다!";
			} else {
				// 비밀번호 일치 여부에 대한 확인
				if(bcryptPasswordEncoder.matches(password, m.getPassword())) {
					msg = "로그인 성공!";
					session.setAttribute("member", m);
				}else {
					msg = "비밀번호가 틀렸습니다.";
				}
			}
			
			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			
		}catch (Exception e) {
			
			throw new MemberException("로그인 에러 발생! : " + e.getMessage());
			
		}
		
		return "common/msg";
	}*/
	
	// Model 객체를 통한 Session 영역의 정보 설정하기
	/*@RequestMapping(value="/member/memberLogin.do", 
			method= RequestMethod.POST)
	public String memberLogin(@RequestParam String userId,
								@RequestParam String password,
								Model model) {
		
		Member m = memberService.selectOne(userId);
		
		String loc = "/";
		String msg = "";
		
		if( m == null) {
			msg = "존재하지 않는 회원입니다.";
		} else {
			if(bcryptPasswordEncoder.matches(password, m.getPassword())) {
				msg = "로그인 성공";
				
				// Model 객체는 일반적으로 데이터 저장시 Request 영역을 사용한다.
				// 하지만 @SessionAttribute 어노테이션을 활용하면
				// 세션 영역에 데이터를 저장할 수 도 있다.
				model.addAttribute("member", m);
			} else {
				msg = "비밀번호가 틀렸습니다.";
			}
		}
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
	}*/
	
	@RequestMapping(value="/member/memberLogin.do", 
			method= RequestMethod.POST)
	public ModelAndView memberLogin(@RequestParam String userId,
								@RequestParam String password) {
		ModelAndView mv = new ModelAndView(); 
		
		Member m = memberService.selectOne(userId);
		
		String loc = "/";
		String msg = "";
		
		if( m == null) {
			msg = "존재하지 않는 회원입니다.";
		} else {
			if(bcryptPasswordEncoder.matches(password, m.getPassword())) {
				msg = "로그인 성공";
				
				// Model 객체는 일반적으로 데이터 저장시 Request 영역을 사용한다.
				// 하지만 @SessionAttribute 어노테이션을 활용하면
				// 세션 영역에 데이터를 저장할 수 도 있다.
				mv.addObject("member", m);
			} else {
				msg = "비밀번호가 틀렸습니다.";
			}
		}
		
		mv.addObject("loc", loc).addObject("msg", msg);
		// mv.addObject("loc", loc)
		// mv.addObject("msg", msg);
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	

	@RequestMapping("/member/memberLogout.do")
	public String memberLogout(SessionStatus sessionStatus, 
			HttpSession session, Model model) {
		
		// 현재 세션의 상태를 완료됨으로 처리함으로써 세션을 종료시키는 방법
		if(!sessionStatus.isComplete()) sessionStatus.setComplete();
		
//		session.invalidate();
		
		String loc ="/";
		String msg = "로그아웃 성공";
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
		
	}

	@RequestMapping("/member/memberView.do")
	public String memberView(@RequestParam String userId) {
		
		return "member/memberView";
	}
	
	@RequestMapping("/member/memberUpdate.do")
	public ModelAndView memberUpdate(Member m) {
		
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.updateMmeber(m);
		
		String loc = "/";
		String msg = "";
		
		if(result > 0) {
			msg = "회원정보 수정 성공!";
			mv.addObject("member", m);
		}else msg = "회원정보 수정 실패!";
		
//		mv.addObject("loc", loc);
//		mv.addObject("msg", msg);
//		mv.setViewName("common/msg");
		
		mv.addObject("msg", msg).addObject("loc", loc).setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/member/memberDelete.do")
	public String memberDelete(SessionStatus sessionStatus,
			Member m, Model model) {
		
		int result = memberService.deleteMember(m.getUserId());
		
		String loc = "/";
		String msg = "";
		
		if(result > 0) {
			msg = "회원 탈퇴 성공!";
			sessionStatus.setComplete();
		}else msg = "회원 탈퇴 실패!";
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
	}
	
	/*********** Spring Ajax ************/
	
	// 1. 일반 입출력 스트림으로 데이터 전송하기
	/*	
	@RequestMapping("/member/checkIdDuplicate.do")
	public void checkDuplicate(@RequestParam String userId, HttpServletResponse res) throws IOException {
		
		boolean isUsable = memberService.checkIdDuplicate(userId) == 0 ? true : false;
		
		res.getWriter().println(isUsable);
	}
	*/
	
	// 2. jsonView(BeanNameViewResolver)를 사용하여 ModelAndView 객체를 반환하고
	//		최종적으로 응답시에 json 방식으로 변환하는 기법
	/*
	@RequestMapping("/member/checkIdDuplicate.do")
	public ModelAndView checkDuplicate(@RequestParam String userId, ModelAndView mv) {
		
		Map map = new HashMap();
		
		
		boolean isUsable = memberService.checkIdDuplicate(userId) == 0 ? true : false;
		map.put("isUsable", isUsable);
		
		mv.addAllObjects(map);
		
		// 중요한 점!!
		// 전달하는 값의 이름과 Javascript에서 찾아오는 형식의 이름이 같아야 한다.
		mv.setViewName("jsonView");
		
		return mv;
	}
	*/
	
	// 3. @ResponseBody 어노테이션과 jackson 라이브러리 활용하기
	/*	
	@RequestMapping("/member/checkIdDuplicate.do")
	@ResponseBody
	public String checkIdDuplicate(@RequestParam String userId) throws JsonProcessingException {
		Map map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = "";
		
		boolean isUsable = memberService.checkIdDuplicate(userId) == 0 ? true : false;
		
		map.put("isUsable", isUsable);
		
		jsonStr = mapper.writeValueAsString(map); // Json형태로 변환
		
		return jsonStr;
	}
   */	
	
	// 4. @ResponseB
	@RequestMapping("/member/checkIdDuplicate.do")
	@ResponseBody
	public Map<String, Object> checkIdDuplicate(@RequestParam String userId){
		Map<String, Object> map = new HashMap<>();
		boolean isUsable = memberService.checkIdDuplicate(userId) == 0 
				? true : false;
		
		map.put("isUsable", isUsable);
		
		return map;
		
	}
	
	/***********************************/
	
}
