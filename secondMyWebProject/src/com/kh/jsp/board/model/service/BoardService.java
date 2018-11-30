package com.kh.jsp.board.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.board.model.vo.Board;
import static com.kh.jsp.common.MySqlSessionFactory.*;
public class BoardService {
	
	private BoardDao bDao = new BoardDao();
	
	// 전체 페이지 계산용 메소드
	public int getListCount(){
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int listCount = bDao.getListCount(ses);
		
		/*close(con);*/
		ses.close();
		
		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		ArrayList<Board> list = bDao.selectList(ses, currentPage, limit);
		
		ses.close();
		
		return list;
	}

	public int insertBoard(Board b) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bDao.insertBoard(ses, b);
		System.out.println("b : " + b);
		
		System.out.println("result : " + result);
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public Board selectOne(int bid) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		Board b = bDao.selectOne(ses, bid);
		
		ses.close();
		
		return b;
	}

	public Board updateView(int bid) {
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		Board b = bDao.selectOne(ses, bid);
		
		ses.close();
		
		return b;
	}

	public int updateBoard(Board b) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bDao.updateBoard(ses, b);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public int deleteBoard(int bid) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bDao.deleteBoard(ses, bid);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public ArrayList<Board> top5() {
		
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		ArrayList<Board> list = bDao.top5(ses);
		
		ses.close();
		
		return list;
		
	}
}





















