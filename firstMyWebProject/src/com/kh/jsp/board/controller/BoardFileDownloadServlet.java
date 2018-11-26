package com.kh.jsp.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownloadServlet
 */
@WebServlet("/bfdown.bo")
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장하고자 하는 파일 이름 가져오기
		String fileName = request.getParameter("path");
		
		// 저장된 폴더의 경로 가져오기
		String folder = request.getServletContext()
				.getRealPath("/resources/boardUploadFiles");
		
		// 파일을 서버에서 클라이언트로 내보낼
		// 스트림 객체 선언
		ServletOutputStream downStream
			= response.getOutputStream();
		
		// 네트워크 통신 시 설정 값이 담기는 헤더 부분에
		// 전송할 파일과 관련된 설정을 등록한다
		response.addHeader("Content-Disposition", 
							"attachment; filename=\""
						   + new String(fileName.getBytes("UTF-8")
								, "ISO-8859-1")+"\"");
		
		File downFile = new File(folder + "/" + fileName);
		
		// 전송한 크기만큼 받아 줄 응답 공간 생성하기
		response.setContentLength((int)downFile.length());
		
		// ----------------------- //
		
		// 폴더에서 버퍼 단위로 꺼내어 전송하기
		BufferedInputStream bin
			= new BufferedInputStream(
					new FileInputStream(downFile));
		
		// 주어진 파일의 바이트 단위로 읽어오기
		int readData = 0;
		
		while((readData = bin.read()) != -1){
			downStream.write(readData);
		}
		
		downStream.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}










