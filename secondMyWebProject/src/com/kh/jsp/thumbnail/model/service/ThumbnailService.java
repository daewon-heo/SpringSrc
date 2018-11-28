package com.kh.jsp.thumbnail.model.service;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.jsp.thumbnail.model.dao.ThumbnailDao;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

public class ThumbnailService {

	private ThumbnailDao tDao = new ThumbnailDao();
	
	public ArrayList<Thumbnail> selectThumbnailList(){
		
		Connection con = getConnection();
		
		ArrayList<Thumbnail> list = tDao.selectList(con);
		
		close(con);
		
		return list;
		
	}

	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		Connection con = getConnection();
		
		int result = 0;
		
		int result1 = tDao.insertThumbnailContent(con, t);
		
		if(result1 > 0){
			int bid = tDao.selectCurrentBid(con);
			
			for(int i = 0; i < list.size(); i++){
				list.get(i).setBid(bid);
			}
			
		}
		
		int result2 = tDao.insertAttachment(con, list);
		
		if( result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public HashMap<String, Object> selectThumbnailMap(int bid) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		
		int result = tDao.updateCount(con, bid);
		
		if(result > 0) {
			commit(con);
			hmap = tDao.selectThumbnailMap(con, bid);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return hmap;
	}
	
	public HashMap<String, Object> updateThumbnailView(int bid) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		
		hmap = tDao.selectThumbnailMap(con, bid);
		
		close(con);
		
		return hmap;
	}

	public int updateThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		Connection con = getConnection();
		
		int result = 0, result1 = 0, result2 = 0;
		
		result1 = tDao.updateThumbnailContent(con, t);
		
		if(result1 > 0){
			result2 = tDao.updateAttachment(con, list);
		}
		
		if( result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
			
		} else rollback(con);
		
		close(con);
		
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
















