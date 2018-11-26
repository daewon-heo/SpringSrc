package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/nInsert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ntitle = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		String userId = request.getParameter("userId");
		
		System.out.println("날짜 : "+date);
		
		Date writeDay = null;
		
		if(date != ""){
			// 날짜가 들어 왔다면
			
			// 2018-10-23 --> 2018, 10, 23
			String[] dateArr = date.split("-");
			
			int[] drr = new int[dateArr.length];
			
			// String --> int
			for(int i = 0; i < dateArr.length;i++){
				drr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDay = new Date(
					new GregorianCalendar(drr[0], drr[1] -1, drr[2]).getTimeInMillis());
			
		} else {
			// 날짜가 들어 오지 않았다면
			
			writeDay = new Date(new GregorianCalendar().getTimeInMillis());
			
		}
		
		Notice n = new Notice();
		
		n.setNtitle(ntitle);
		n.setNcontent(content);
		n.setNwriter(userId);
		n.setNdate(writeDay);
		
		NoticeService ns = new NoticeService();
		
		int result = ns.insertNotice(n);
		
		if(result > 0){
			
			response.sendRedirect("selectList.no");
			
		} else {
			
			request.setAttribute("msg", "공지사항 등록 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
