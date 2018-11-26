package com.kh.jsp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/bInsert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 처리 용 MultipartRequest //
		
		// 1. 업로드할 파일의 최대 크기를 설정
		// 10MB (1MB --> 1024KB / 1KB --> 1024Byte)
		int maxSize = 1024 * 1024 * 10;
		
		// 2. multipart/form-data 로 전송되었는 지 확인!
		if(!ServletFileUpload.isMultipartContent(request)){
			request.setAttribute("msg", "enctype 을 통한 multipart 전송이 필요합니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
		}
		
		// 3. 웹 상의 루트(최상위) 경로를 활용하여
		// 저장할 폴더 위치를 선정한다.
		String root = request.getServletContext()
				.getRealPath("/");
		
		// 업로드할 폴더 명을 붙여준다.
		
		String savePath
		= root + "resources/boardUploadFiles";
		
		// 실제 담아온 파일 및 정보들을 추출한다.
		// request --> MultipartRequest
		
		MultipartRequest mrequest = new MultipartRequest(
				request, // 기존의 request 객체
				savePath, // 파일 저장 경로
				maxSize, // 파일 최대 크기
				"UTF-8", // 인코딩 방식
				new DefaultFileRenamePolicy()
					// 만약에 동일한 이름의 파일이 저장된다면
					// 새로운 이름을 부여하여 기존의 
					// 파일 명과 구분해주는 정책
					// 000.txt ---> 0001.txt
				);
		
		// -- 파일 업로드 관련 로직 처리 -- //
		
		// 기본 전송 값 처리 로직
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		String writer = mrequest.getParameter("userId");
		
		// 파일 전송 로직
		// 전달받은 파일을 저장하고 해당 파일의 이름을 가져오는 메소드
		String fileName = mrequest.getFilesystemName("file");
		
		Board b = new Board();
		
		b.setBtitle(title);
		b.setBcontent(content);
		b.setBwriter(writer);
		b.setBoardfile(fileName);
		
		int result = new BoardService().insertBoard(b);
		
		if( result > 0) {
			
			response.sendRedirect("selectList.bo");
			
		} else {
			
			request.setAttribute("msg", "게시글 작성 실패!");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

























