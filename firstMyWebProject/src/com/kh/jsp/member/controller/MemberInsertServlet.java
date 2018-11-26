package com.kh.jsp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		
		String phone = request.getParameter("tel1") + "-"
				     + request.getParameter("tel2") + "-"
				     + request.getParameter("tel3");
		
		String address = request.getParameter("zipCode") + ", "
				       + request.getParameter("address1") + ", "
				       + request.getParameter("address2");
		
		String hobby
		    = String.join(", " , request.getParameterValues("hobby"));
		
		MemberService ms = new MemberService();
		
		Member m = new Member(userId, password, userName,
				              gender, age, email,
				              phone, address, hobby);
		
		// 회원 가입 확인용 메소드
		try{
			ms.insertMember(m);
			System.out.println("회원 가입 완료! : " + m);
			response.sendRedirect("index.jsp");
			
		} catch(Exception e) {
			request.setAttribute("msg", "회원 가입 중 에러가 발생하였습니다.");
			request.setAttribute ("exception", e);
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
