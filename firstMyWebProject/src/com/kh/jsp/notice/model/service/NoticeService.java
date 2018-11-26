package com.kh.jsp.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.notice.model.dao.NoticeDao;
import com.kh.jsp.notice.model.vo.Notice;

import static com.kh.jsp.common.JDBCTemplate.*;

public class NoticeService {

	private NoticeDao nDao = new NoticeDao();
	
	public ArrayList<Notice> selectList(){
		
		ArrayList<Notice> list = null;
		Connection con = getConnection();
		
		list = nDao.selectList(con);
		
		close(con);
		
		return list;
	}

	public Notice selectOne(int nno) {
		// 상세 보기 조회 시
		// 처리할 서비스 상세 기능 
		// 1. nno에 해당하는 공지 글 한 개 조회하여 가져오기
		// 2. 게시글 한 개를 성공적으로 가져왔다면 조회수를 1 증가시킨다.
		
		Connection con = getConnection();
		
		int result = 0;
		
		Notice n = nDao.selectOne(con, nno);
		
		if( n != null ){
			result = nDao.updateCount(con, nno);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		close(con);
		
		return n;
	}

	public int insertNotice(Notice n) {
		int result = 0;
		
		Connection con = getConnection();
		
		result = nDao.insertNotice(con, n);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Notice updateView(int nno) {
		Notice n = null;
		
		Connection con = getConnection();
		
		n = nDao.selectOne(con, nno);
		
		return n;
	}

	public int updateNotice(Notice n) {
		Connection con = getConnection();
		
		int result = nDao.updateNotice(con, n);
		
		if( result > 0) commit(con);
		else rollback(con);
		
		return result;
	}

	public ArrayList<Notice> searchNotice(String condition, String keyword) {
		Connection con = getConnection();
		ArrayList<Notice> list = null;
		
		// if(condition.length() > 0 ) list = nDao.searchNotice(con, condition, keyword);
		// else list = nDao.selectList(con);
		
		list = (condition.length() > 0) ? 
				nDao.searchNotice(con, condition, keyword) : nDao.selectList(con); 
		
		return list;
	}

	public void deleteNotice(int nno) {
		// TODO Auto-generated method stub
		
	}
}











