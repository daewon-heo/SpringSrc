package com.kh.jsp.board.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.board.model.vo.Board;

public class BoardService {
	
	private BoardDao bDao = new BoardDao();
	
	// 전체 페이지 계산용 메소드
	public int getListCount(){
		Connection con = getConnection();
		
		int listCount = bDao.getListCount(con);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<Board> list = bDao.selectList(con, currentPage, limit);
		
		close(con);
		
		return list;
	}

	public int insertBoard(Board b) {
		Connection con = getConnection();
		
		int result = bDao.insertBoard(con, b);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Board selectOne(int bid) {
		Connection con = getConnection();
		
		Board b = bDao.selectOne(con, bid);
		
		close(con);
		
		return b;
	}

	public Board updateView(int bid) {
		Connection con = getConnection();
		
		Board b = bDao.selectOne(con, bid);
		
		close(con);
		
		return b;
	}

	public int updateBoard(Board b) {
		Connection con = getConnection();
		
		int result = bDao.updateBoard(con, b);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bid) {
		Connection con = getConnection();
		
		int result = bDao.deleteBoard(con, bid);
		
		close(con);
		
		return result;
	}

	public ArrayList<Board> top5() {
		
		Connection con = getConnection();
		
		ArrayList<Board> list = bDao.top5(con);
		
		close(con);
		
		return list;
		
	}
}





















