package com.kh.jsp.thumbnail.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import static com.kh.jsp.common.JDBCTemplate.*;

import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

public class ThumbnailDao {

	private Properties prop = new Properties();
	
	public ThumbnailDao(){
		
		String filePath = ThumbnailDao.class
				.getResource("/config/thumbnail-query.properties").getPath();
		
		try {
		
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Thumbnail> selectList(SqlSession ses) {
		
		return new ArrayList<Thumbnail>(
				ses.selectList("Thumbnail_mapper.selectList"));
	}

	public int insertThumbnailContent(SqlSession ses, Thumbnail t) {
		
		return ses.insert("Thumbnail_mapper.insertThumbnail", t);
	}

	public int selectCurrentBid(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int bid = 0;
		
		String sql = prop.getProperty("selectCurrentBid");
		
		try {
			stmt = con.createStatement();
		
			rset = stmt.executeQuery(sql);
			
			if(rset.next()){
				bid = rset.getInt(1); // "CURRVAL"
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bid;
	}

	public int insertAttachment(Connection con, ArrayList<Attachment> list) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertAttachment");
		
		try{
			
			for(int i = 0 ; i < list.size(); i++){
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, list.get(i).getBid());
				pstmt.setString(2, list.get(i).getOriginName());
				pstmt.setString(3, list.get(i).getChangeName());
				pstmt.setString(4, list.get(i).getFilePath());
				
				// 첫번째 데이터일 경우 대표 이미지로 level = 0
				// 나머지 데이터는 일반 이미지로 level = 1
				
				int level = 0;
				if(i != 0 ) level = 1;
				
				pstmt.setInt(5, level);
				
				result += pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public HashMap<String, Object> selectThumbnailMap(SqlSession ses, int bid) {
		
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		
		Thumbnail t = ses.selectOne(
				"Thumbnail_mapper.selectThumbnailOne", bid);
		
		ArrayList<Attachment> list = t.getAttachments();
		
		if( t!= null){
			hmap.put("thumbnail", t);
			hmap.put("attachment", list);
		}
		
		return hmap;
	}

	// 게시글 조회수 증가용
	public int updateCount(SqlSession ses, int bid) {
		
		return ses.update("Thumbnail_mapper.updateCount", bid);
	}

	public int updateThumbnailContent(SqlSession ses, Thumbnail t) {
		
		return ses.update("Thumbnail_mapper.updateThumbnailContent", t);
	}

	public int updateAttachment(SqlSession ses, ArrayList<Attachment> list) {
		
		return ses.update("Thumbnail_mapper.updateAttachment", list);
	}

	public int deleteThumbnail(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteThumbnail");
		
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteAttachment(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}


















