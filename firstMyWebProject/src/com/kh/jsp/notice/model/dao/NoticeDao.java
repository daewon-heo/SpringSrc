package com.kh.jsp.notice.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.jsp.common.JDBCTemplate.*;

import com.kh.jsp.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = null;
	
	public NoticeDao(){
		prop = new Properties();
		
		String filePath = NoticeDao.class
				.getResource("/config/notice-query.properties").getPath();
		
		try {
		
			prop.load(new FileReader(filePath));
		
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Notice> selectList(Connection con){
		ArrayList<Notice> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<Notice>();
			
			while(rset.next()){
				Notice n = new Notice();
				
				n.setNno(rset.getInt("nno"));
				n.setNtitle(rset.getString("ntitle"));
				n.setNcontent(rset.getString("ncontent"));
				n.setNwriter(rset.getString("USERNAME"));
				n.setNcount(rset.getInt("ncount"));
				n.setNdate(rset.getDate("ndate"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}


	public Notice selectOne(Connection con, int nno) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
		
			pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				n = new Notice();
				
				n.setNno(nno);
				n.setNtitle(rset.getString(2));
				n.setNcontent(rset.getString(3));
				n.setNwriter(rset.getString("USERNAME"));
				n.setNcount(rset.getInt(5));
				n.setNdate(rset.getDate(6));
			}
			
			System.out.println("notice 한 개 : " + n);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}


	public int updateCount(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateCount");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}


	public int insertNotice(Connection con, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setString(3, n.getNwriter());
			pstmt.setDate(4, n.getNdate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updateNotice(Connection con, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateNotice");
		
		try{ 
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setInt(3, n.getNno());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}


	public ArrayList<Notice> searchNotice(Connection con, String condition, String keyword) {
		
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "writer" :
			sql = prop.getProperty("searchWriterNotice");
			break;
		case "title" :
			sql = prop.getProperty("searchTitleNotice");
			break;
		case "content" :
			sql = prop.getProperty("searchContentNotice");
			break;
		}
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()){
				
				Notice n = new Notice();
				
				n.setNno(rset.getInt("nno"));
				n.setNtitle(rset.getString("ntitle"));
				n.setNcontent(rset.getString("ncontent"));
				n.setNwriter(rset.getString("username"));
				n.setNcount(rset.getInt("ncount"));
				n.setNdate(rset.getDate("ndate"));
				
				list.add(n);
				
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		// 확인용 출력문
		for(Notice n : list) System.out.println(n);
		
		return list;
	}
}













