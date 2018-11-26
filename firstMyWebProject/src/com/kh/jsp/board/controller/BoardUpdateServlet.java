package com.kh.jsp.board.controller;

import java.io.File;
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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/bUpdate.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// --- 첨부 파일 유무를 확인 하기 위한 코드 --- //
		
		if(!ServletFileUpload.isMultipartContent(request)){
			request.setAttribute("msg", "multipart/form-data로 전송해야만 합니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
		}
		
		// 저장할 파일 최대 크기와 파일의 저장 경로를 지정한다.
		int maxSize = 1024 * 1024 * 10;
		
		String root = request.getServletContext().getRealPath("/");
		
		String savePath = root + "resources/boardUploadFiles";
		
		// Request --> MultipartRequest
		MultipartRequest mrequest = new MultipartRequest(
					 request,
					 savePath,
					 maxSize,
					 "UTF-8",
					 new DefaultFileRenamePolicy());
		
		BoardService bs = new BoardService();
		
		int bid = Integer.parseInt(mrequest.getParameter("bno"));
		
		Board b = bs.selectOne(bid);
		
		// -- 기본 형식 값 -- //
		
		String title = mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		b.setBtitle(title);
		b.setBcontent(content);
		
		// -- 파일 저장 유무 확인 로직 -- //
		
		String file = mrequest.getFilesystemName("file");
		
		if( file != null ){
			
			File originFile = new File(savePath + "/"+b.getBoardfile());
			
			originFile.delete();
			
			b.setBoardfile(file);
			
		}
		
		int result = bs.updateBoard(b);
		
		String page = "";
		
		if( result > 0 ) {
			
			page = "views/board/boardDetail.jsp";
			request.setAttribute("board", b);
			
		} else {
			
			page= "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 수정 실패!!");
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}







