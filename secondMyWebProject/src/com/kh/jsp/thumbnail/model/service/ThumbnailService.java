package com.kh.jsp.thumbnail.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;
import static com.kh.jsp.common.MySqlSessionFactory.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.jsp.thumbnail.model.dao.ThumbnailDao;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

public class ThumbnailService {

	private ThumbnailDao tDao = new ThumbnailDao();
	
	public ArrayList<Thumbnail> selectThumbnailList(){
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		ArrayList<Thumbnail> list = tDao.selectList(ses);
		
		ses.close();
		
		return list;
		
	}

	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		SqlSession ses = getSqlSessionFactory()
				.openSession(false);
		
		t.setAttachments(list);
		
		int result = tDao.insertThumbnailContent(ses, t);
		
		if(result > 0) ses.commit();
		else ses.rollback();
		
		ses.close();
		
		return result;
	}
	
	public HashMap<String, Object> selectThumbnailMap(int bid) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		HashMap<String, Object> hmap = null;
		
		int result = tDao.updateCount(ses, bid);
		
		if(result > 0) {
			ses.commit();
			hmap = tDao.selectThumbnailMap(ses, bid);
		}else {
			ses.rollback();
		}
		
		ses.commit();
		
		return hmap;
	}
	
	public HashMap<String, Object> updateThumbnailView(int bid) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		HashMap<String, Object> hmap = null;
		
		hmap = tDao.selectThumbnailMap(ses, bid);
		
		ses.close();
		
		return hmap;
	}

	public int updateThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = 0, result1 = 0, result2 = 0;
		
		result1 = tDao.updateThumbnailContent(ses, t);
		
		if(result1 > 0){
			result2 = tDao.updateAttachment(ses, list);
		}
		
		if( result1 > 0 && result2 > 0) {
			ses.commit();
			result = 1;
			
		} else ses.rollback();
		
		ses.close();
		
		return result;
	}

	public int deleteThumbnail(int bid) {
		Connection con = getConnection();
		
		int result = 0, result1 = 0, result2 = 0;
		
		result1 = tDao.deleteThumbnail(con, bid);
		
		if(result1 > 0){
			result2 = tDao.deleteAttachment(con, bid);
		}
		
		if( result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result;
	}
}
















