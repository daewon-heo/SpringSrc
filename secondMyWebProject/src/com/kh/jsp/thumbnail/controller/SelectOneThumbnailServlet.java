package com.kh.jsp.thumbnail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.boardComment.model.service.BoardCommentService;
import com.kh.jsp.boardComment.model.vo.BoardComment;
import com.kh.jsp.thumbnail.model.service.ThumbnailService;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

/**
 * Servlet implementation class SelectOneThumbnailServlet
 */
@WebServlet("/selectOne.tn")
public class SelectOneThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = Integer.parseInt(request.getParameter("bno"));
		
		HashMap<String, Object> thumb = new ThumbnailService().selectThumbnailMap(bid);
		
		System.out.println(thumb);
				
		String page = "";
		if(thumb != null && thumb.get("attachment") != null) {
			
			page = "views/thumbnail/thumbnailDetail.jsp";
			request.setAttribute("thumbnail", thumb.get("thumbnail"));
			request.setAttribute("fileList", thumb.get("attachment"));
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "사진게시판 상세보기 실패");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
