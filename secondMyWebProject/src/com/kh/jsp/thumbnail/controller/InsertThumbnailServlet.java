package com.kh.jsp.thumbnail.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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
 * Servlet implementation class InsertThumbnailServlet
 */
@WebServlet("/tInsert.tn")
public class InsertThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThumbnailService ts = new ThumbnailService();
		
		if(ServletFileUpload.isMultipartContent(request)){
			// 만약 multipart/form-data 로 전송이 되었다면 실행해라!
			
			// 전송할 파일의 용량 선정
			int maxSize = 1024 * 1024 * 10;
			
			// 저장할 경로 설정하기
			String root = request.getServletContext().getRealPath("/resources");
			
			System.out.println("root 경로 확인 : " + root);
			
			String savePath = root + "/thumbnailUploadFiles/";
			
			// 사용자가 저장하는 파일을 서버의 형식에 맞게
			// 이름을 변경하여 설정하기
			// ex) kakaoTalk_20181029_00000.jpg
			
			// DefaultFileRenamePolicy 의 경우 같은 파일이 이미 존재하는 지 검사한 후에
			// 만약 존재한다면 파일명 뒤에 숫자를 붙여서 이름을 변경한다.
			// abc.zip --> abc1.zip ---> abc2.zip
			
			// 직접 파일 이름을 변경하여 사용함으로써 관리의 효율성을 증대 시킨다.
			
			MultipartRequest mrequest =
					new MultipartRequest(request, 
										 savePath,
										 maxSize,
										 "UTF-8",
										 new MyRenamePolicy());
			
			// 다중 파일 업로드 시 처리하는 방법
			// 다중 파일을 업로드 할 경우 컬렉션을 사용하여
			// 파일을 별도로 관리한다.
			
			ArrayList<String> saveFiles = new ArrayList<String>();
			
			// 원본 파일 명
			ArrayList<String> originFiles = new ArrayList<String>();
			
			// 폼으로 전송된 파일 이름들을 받아온다.
			Enumeration<String> files
			 = mrequest.getFileNames();
			
			while(files.hasMoreElements()){
				// 각 파일의 정보를 가져와서 DB에 저장할 내용을 추출한다.
				
				String name = files.nextElement();
				
				System.out.println("name : " + name);
				
				saveFiles.add(mrequest.getFilesystemName(name));
				originFiles.add(mrequest.getOriginalFileName(name));				
			}
			
			// Thumbnail 객체 생성 후 DB 전달 VO 설정하기
			
			Thumbnail t = new Thumbnail();
			
			t.setBtitle(mrequest.getParameter("title"));
			t.setBcontent(mrequest.getParameter("content"));
			t.setBwriter(mrequest.getParameter("userId"));
			
			// Attachment에 기록하기 위한 파일 리스트 처리하기
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			
			for(int i = originFiles.size() -1 ; i >= 0 ; i--){
				// 기존에 역순으로 저장된 파일 리스트를 올바른 순서로 재정렬하기
				Attachment at = new Attachment();
				
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				System.out.println("at : " + at);
				list.add(at);
			}
			
			// service로 작성한 내용 전송하기
			
			int result = ts.insertThumbnail(t, list);
			
			if(result > 0) {
				response.sendRedirect("selectList.tn");
				
			} else {
				request.setAttribute("msg", "파일 전송 실패!");
				
				// 실패했을 때 이전 파일 정보 삭제하기
				for(int i = 0; i < saveFiles.size() ; i++){
					File file = new File(savePath+saveFiles.get(i));
					
					System.out.println(i+"번 파일 삭제 : " + file.delete());
				}
				
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
