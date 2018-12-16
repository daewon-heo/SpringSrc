package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.util.Utils;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/boardList.do")
	public String selectBoardList(
			@RequestParam(value="cPage", required=false, defaultValue="1")
			int cPage, Model model){
		
		int numPerPage = 10; // 한 페이지당 게시글 수
		
		// 1. 현재 페이지 게시글 목록 가져오기
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>(boardService.selectBoardList(cPage, numPerPage));
		
		// 2. 전체 게시글 개수 가져오기 
		int totalContents = boardService.selectBoardTotalContents();
		
		// 3. 페이지 계산 후 작성할 HTML 추가
		String pageBar = Utils.getPageBar(totalContents, cPage, numPerPage, "boardList.do");
		
		model.addAttribute("list", list)
		.addAttribute("totalContents", totalContents)
		.addAttribute("numPerPage",numPerPage)
		.addAttribute("pageBar", pageBar);
		
		return "board/boardList";
		
	}
	
	@RequestMapping("/board/boardForm.do")
	public void boardForm() {
		// ViewNameTranslator 객체를 통해
		// view 이름이 지정되지 않았을 경우 url의 경로를 통해
		// view가 받을 이름을 유추하여 연결 짓는다.
	}
	
	@RequestMapping("/board/boardFormEnd.do")
	public String insertBoard(Board board, Model model, HttpSession session, 
			@RequestParam(value="upFile", required=false) MultipartFile[] upFile) {
		
		// 1. 파일을 저장할 경로 생성
		String saveDir = session.getServletContext()
				.getRealPath("/resources/upload/board");
		
		List<Attachment> attachList = new ArrayList<Attachment>();
		
		// 2. 폴더 유무 확인 후 생성
		File dir = new File(saveDir);
		
		System.out.println("폴더가 있나요? " + dir.exists());
		
		if(dir.exists() == false) dir.mkdirs();
		
		// 3. 파일 업로드 시작
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) {
				String originName = f.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf(".")+1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
				
				int rnNum = (int)(Math.random() * 1000);
				
				// 서버에서 저장후 관리할 파일 명
				String nenamedName = sdf.format(new Date()) + "_" + rnNum + "." + ext;
				
				// 실제 파일을 지정한 파일명으로 변환하며 데이터를 저장한다.
				try {
					f.transferTo(new File(saveDir + "/" + nenamedName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				Attachment at = new Attachment();
				at.setOriginalFileName(originName);
				at.setRenamedFileName(nenamedName);
				
				attachList.add(at);
			}
		}
		
		int result;
		
		try {
			result = boardService.insertBoard(board, attachList);
		}catch (Exception e) {
			throw new BoardException("게시글 등록 오류!!");
		}
		
		String loc = "/";
		String msg = "";
		
		if(result > 0) {
			msg =  "게시글 등록 성공";
		}else {
			msg=  "게시글 등록 실패";
		}
		
		model.addAttribute("loc", loc)
		.addAttribute("msg", msg);
		
		return "common/msg";
	}
	
	@RequestMapping("/board/boardView.do")
	public String selecOne(@RequestParam int no, Model model) {
		
		model.addAttribute("board", boardService.selectOneBoard(no))
		.addAttribute("attachmentList",boardService.selectAttachmentList(no));
		
		return "board/boardView";
	}
	
	@RequestMapping("/board/boardUpdateView.do")
	public String updateBoardView(@RequestParam int boardNo, Model model) {
		
		model.addAttribute("board", boardService.selectOneBoard(boardNo))
		.addAttribute("attachmentList", boardService.selectAttachmentList(boardNo));
		
		return "/board/boardUpdateView";
	}
	
	@RequestMapping("/board/boardUpdate.do")
	public String updateBoard(@RequestParam int boardNo, Board board, Model model, HttpSession session, 
			@RequestParam(value="upFile", required=false) MultipartFile[] upFile) {
		
		System.out.println("보드 : " + board);
		// 1. 파일을 저장할 경로 생성
		String saveDir = session.getServletContext()
				.getRealPath("/resources/upload/board");
		
		List<Attachment> attachList = new ArrayList<Attachment>();
		
		// 2. 폴더 유무 확인 후 생성
		File dir = new File(saveDir);
		
		System.out.println("폴더가 있나요? " + dir.exists());
		
		if(dir.exists() == false) dir.mkdirs();
		
		// 3. 파일 업로드 시작
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) {
				String originName = f.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf(".")+1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
				
				int rnNum = (int)(Math.random() * 1000);
				
				// 서버에서 저장후 관리할 파일 명
				String nenamedName = sdf.format(new Date()) + "_" + rnNum + "." + ext;
				
				// 실제 파일을 지정한 파일명으로 변환하며 데이터를 저장한다.
				try {
					f.transferTo(new File(saveDir + "/" + nenamedName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				Attachment at = new Attachment();
				at.setOriginalFileName(originName);
				at.setRenamedFileName(nenamedName);
				
				attachList.add(at);
			}
		}
		
		int result;
		
		try {
			board.setBoardNo(boardNo);
			result = boardService.updateBoard(board, attachList);
		}catch (Exception e) {
			throw new BoardException("게시글 수정 오류!!");
		}
		
		String loc = "/";
		String msg = "";
		
		if(result > 0) {
			msg =  "게시글 수정 성공";
		}else {
			msg=  "게시글 수정 실패";
		}
		
		model.addAttribute("loc", loc)
		.addAttribute("msg", msg);
		
		return "common/msg";
	}

	@RequestMapping("/board/boardDelete.do")
	public String deleteBoard(@RequestParam int boardNo, Model model) {
		
		int result = boardService.deleteBoard(boardNo);
		
		String loc = "/";
		String msg = "";
		
		if(result > 0) {
			msg = "게시글 삭제 성공";
		}else {
			msg = "게시글 삭제 실패";
		}
		
		model.addAttribute("msg", msg).addAttribute("loc", loc);
		
		return "common/msg";
	}
}
