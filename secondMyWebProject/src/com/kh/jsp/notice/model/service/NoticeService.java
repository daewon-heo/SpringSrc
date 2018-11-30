package com.kh.jsp.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;

import static com.kh.jsp.common.JDBCTemplate.*;
import static com.kh.jsp.common.MySqlSessionFactory.*;

public class NoticeService {

	private NoticeDao nDao = new NoticeDao();
	
	public ArrayList<Notice> selectList(){
		
		ArrayList<Notice> list = null;
		/*Connection con = getConnection();*/
		
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		list = nDao.selectList(ses);
		
		/*close(con);*/
		ses.close();
		
		return list;
	}

	public Notice selectOne(int nno) {
		// 상세 보기 조회 시
		// 처리할 서비스 상세 기능 
		// 1. nno에 해당하는 공지 글 한 개 조회하여 가져오기
		// 2. 게시글 한 개를 성공적으로 가져왔다면 조회수를 1 증가시킨다.
		
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = 0;
		
		Notice n = nDao.selectOne(ses, nno);
		
		if( n != null ){
			result = nDao.updateCount(ses, nno);
			
			if(result > 0) ses.commit();
			else ses.rollback();
		}
		
		ses.close();
		
		return n;
	}

	public int insertNotice(Notice n) {
		int result = 0;
		
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		result = nDao.insertNotice(ses, n);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public Notice updateView(int nno) {
		Notice n = null;
		
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		n = nDao.selectOne(ses, nno);
		
		return n;
	}

	public int updateNotice(Notice n) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = nDao.updateNotice(ses, n);
		
		if( result > 0) ses.commit();
		else ses.rollback();
		
		return result;
	}

	public ArrayList<Notice> searchNotice(String condition, String keyword) {
//		Connection con = getConnection();
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		HashMap<String, String> hmap = new HashMap<>();
		
		hmap.put("condition", condition);
		hmap.put("keyword", keyword);
		
		ArrayList<Notice> list = nDao.searchNotice(ses, hmap);
		
		return list;
	}

	public void deleteNotice(int nno) {
		
	}
}











