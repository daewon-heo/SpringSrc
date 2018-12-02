package com.kh.jsp.boardComment.model.service;

import com.kh.jsp.boardComment.model.dao.BoardCommentDao;
import com.kh.jsp.boardComment.model.vo.BoardComment;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import static com.kh.jsp.common.MySqlSessionFactory.*;
public class BoardCommentService {

	private BoardCommentDao bcDao = new BoardCommentDao();
	
	public ArrayList<BoardComment> selectList(int bid){

		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		ArrayList<BoardComment> clist = bcDao.selectList(ses, bid);
		
		ses.close();
		
		return clist;
		
	}
	
	public int insertComment(BoardComment bco) {
		
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bcDao.insertComment(ses, bco);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public BoardComment selectOne(int cno) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		BoardComment bco = bcDao.selectOne(ses, cno);
		
		ses.close();
		
		return bco;
	}

	public int updateComment(BoardComment bco) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bcDao.updateComment(ses, bco);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}
	
	public int deleteComment(int cno) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = bcDao.deleteComment(ses, cno);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

}
