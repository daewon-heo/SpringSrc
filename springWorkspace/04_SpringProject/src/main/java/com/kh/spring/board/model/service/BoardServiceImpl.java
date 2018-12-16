package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<Map<String, String>> selectBoardList(int cPage, int numPerPage) {
		return boardDao.selectBoardList(cPage,numPerPage);
	}

	@Override
	public int selectBoardTotalContents() {
		return boardDao.selectBoardTotalContents();
	}

	@Override
	/*@Transactional(propagation=Propagation.REQUIRED, 
				   isolation=Isolation.READ_COMMITTED)*/
	public int insertBoard(Board board, List<Attachment> attachList) {
		System.out.println("boardservice : 리스트 " + attachList);
		int result = 0;
		int boardNo = 0;
		
		try{
			result = boardDao.insertBoard(board);
			if(result==0) throw new BoardException();
			
			boardNo = board.getBoardNo(); //boardNo를 리턴함.
			logger.debug("boardNo="+boardNo);
			
			//현재 Attachment객체의 boardNo는 값이 없다. 
			//1. 가져온 boardNo를 대입하던지
			//2. mapper의 insert문에서 selectKey를 사용함
			if(attachList.size()>0){
				for(Attachment a : attachList){
					// a.setBoardNo(boardNo); //게시물번호 대입
					result = boardDao.insertAttachment(a);
					if(result==0) throw new BoardException();
				}
			}
		} catch(Exception e){
			logger.debug("게시물등록 에러");
//			throw new BoardException("게시물등록오류");
			throw e;
		}
		return result;
	}

	@Override
	public Board selectOneBoard(int boardNo) {
		return boardDao.selectOneBoard(boardNo);
	}

	@Override
	public List<Attachment> selectAttachmentList(int boardNo) {
		return boardDao.selectAttachmentList(boardNo);
	}

}
