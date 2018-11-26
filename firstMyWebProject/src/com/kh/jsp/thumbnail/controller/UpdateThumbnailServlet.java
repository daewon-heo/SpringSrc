package com.kh.jsp.thumbnail.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.common.MyRenamePolicy;
import com.kh.jsp.thumbnail.model.service.ThumbnailService;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpdateThumbnailServlet
 */
@WebServlet("/tUpdate.tn")
public class UpdateThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ThumbnailService ts = new ThumbnailService();
		
		if(ServletFileUpload.isMultipartContent(request)){
			
			int maxSize = 1024 * 1024 * 10;
			
			String root = request.getServletContext().getRealPath("/resources");
			
			System.out.println("root 경로 확인 : " + root);
			
			String savePath = root + "/thumbnailUploadFiles/";
			
			MultipartRequest mrequest =
					new MultipartRequest(request, savePath, maxSize,
										 "UTF-8", new MyRenamePolicy());
			
			int bid = Integer.parseInt(mrequest.getParameter("bno"));
			HashMap<String, Object> thumb = ts.selectThumbnailMap(bid);
			
			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();
			
			// 폼으로 전송된 파일 이름들을 받아온다.
			Enumeration<String> files = mrequest.getFileNames();
			
			while(files.hasMoreElements()){
				String name = files.nextElement();
				
				System.out.println("name : " + name);
				
				saveFiles.add(mrequest.getFilesystemName(name));
				originFiles.add(mrequest.getOriginalFileName(name));				
			}
			
			Thumbnail t = (Thumbnail)thumb.get("thumbnail");
			
			t.setBtitle(mrequest.getParameter("title"));
			t.setBcontent(mrequest.getParameter("content"));
			t.setBwriter(mrequest.getParameter("userId"));
			
			ArrayList<Attachment> list = (ArrayList<Attachment>)thumb.get("attachment");
			
			System.out.println(list);
			
			for(int i = originFiles.size() -1 ; i >= 0 ; i--){
				int j = originFiles.size() - i - 1;
				if(originFiles.get(i) != null) {
					new File(savePath+list.get(j).getChangeName()).delete();
					
					list.get(j).setFilePath(savePath);
					list.get(j).setOriginName(originFiles.get(i));
					list.get(j).setChangeName(saveFiles.get(i));
					
				}
			}
			
			// service로 작성한 내용 전송하기
			int result = ts.updateThumbnail(t, list);
			
			if(result > 0) {
				response.sendRedirect("selectList.tn");
				
			} else {
				request.setAttribute("msg", "파일 전송 실패!");
				
				request.getRequestDispatcher("views/common/errorPage.jsp")
				.forward(request, response);
			}
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
