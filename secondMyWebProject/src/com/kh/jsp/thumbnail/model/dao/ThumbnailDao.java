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
	
	public ArrayList<Thumbnail> selectList(Connection con) {
		
		Statement stmt = null;
		ArrayList<Thumbnail> list = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			stmt = con.createStatement();
		
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<Thumbnail>();
			
			while(rset.next()){
				Thumbnail t = new Thumbnail();
				
				t.setBid(rset.getInt("BID"));
				t.setBno(rset.getInt("BNO"));
				t.setBtitle(rset.getString("BTITLE"));
				t.setBwriter(rset.getString("USERNAME"));
				t.setBcount(rset.getInt("BCOUNT"));
				t.setBdate(rset.getDate("BDATE"));
				t.setBoardfile(rset.getString("CHANGENAME"));
				
				list.add(t);
			}
			
		} catch (SQLException e) {
		 
			e.printStackTrace();
			
		} finally {
			
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertThumbnailContent(Connection con, Thumbnail t) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, t.getBtitle());
			pstmt.setString(2, t.getBcontent());
			pstmt.setString(3, t.getBwriter());
			pstmt.setNull(4,  Types.NULL);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
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

	public HashMap<String, Object> selectThumbnailMap(Connection con, int bid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Object> hmap = null;
		Thumbnail t = null;
		Attachment at = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectThumbnailOne");
				
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			
			//System.out.println(rset.getArray(1));
			
			while(rset.next()){
				
				t = new Thumbnail();
				t.setBid(bid);
				t.setBno(rset.getInt("bno"));
				t.setBtitle(rset.getString("btitle"));
				t.setBcontent(rset.getString("bcontent"));
				t.setBwriter(rset.getString("username"));
				t.setBcount(rset.getInt("bcount"));
				t.setBdate(rset.getDate("bdate"));
				
				at = new Attachment();
				at.setFno(rset.getInt("fno"));
				at.setOriginName(rset.getString("originname"));
				at.setChangeName(rset.getString("changename"));
				at.setFilePath(rset.getString("filepath"));
				at.setUploadDate(rset.getDate("uploaddate"));
				
				System.out.println(at);
				
				list.add(at);
				
			}
			hmap = new HashMap<String, Object>();
			
			hmap.put("thumbnail", t);
			hmap.put("attachment", list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hmap;
	}

	public int updateCount(Connection con, int bid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateCount");
		
		try{
			
			pstmt = con.prepareStatement(sql);
				
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateThumbnailContent(Connection con, Thumbnail t) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, t.getBtitle());
			pstmt.setString(2, t.getBcontent());
			pstmt.setInt(3,  t.getBid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection con, ArrayList<Attachment> list) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateAttachment");
		
		try{
			
			for(int i = 0 ; i < list.size(); i++){
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, list.get(i).getOriginName());
				pstmt.setString(2, list.get(i).getChangeName());
				pstmt.setInt(3, list.get(i).getFno());
				
				result += pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
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


















