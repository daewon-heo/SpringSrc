package com.kh.jsp.boardComment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jsp.boardComment.model.vo.BoardComment;

import static com.kh.jsp.common.JDBCTemplate.*;

public class BoardCommentDao {
private Properties prop = new Properties();
	
	public BoardCommentDao(){
		String filePath = BoardCommentDao.class
				.getResource("/config/comment-query.properties").getPath();
		try {
		
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}

	public int insertComment(Connection con, BoardComment bco) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertComment");
		System.out.println(bco);
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bco.getBid());
			pstmt.setString(2, bco.getCcontent());
			pstmt.setString(3, bco.getCwriter());
			
			if(bco.getRefcno() > 0) {
				
				pstmt.setInt(4, bco.getRefcno());
			} else {
				
				pstmt.setNull(4, java.sql.Types.NULL);
			}
			pstmt.setInt(5, bco.getClevel());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public ArrayList<BoardComment> selectList(Connection con, int bid) {
		ArrayList<BoardComment> clist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			
			clist = new ArrayList<BoardComment>();
			
			while(rset.next()) {
				BoardComment comment = new BoardComment();
				
				comment.setCno(rset.getInt("CNO"));
				comment.setBid(bid);
				comment.setCcontent(rset.getString("CCONTENT"));
				comment.setCwriter(rset.getString("USERNAME"));
				comment.setCdate(rset.getDate("CDATE"));
				comment.setRefcno(rset.getInt("REFCNO"));
				comment.setClevel(rset.getInt("CLEVEL"));
				
				clist.add(comment);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return clist;
	}

	public BoardComment selectOne(Connection con, int cno) {
		BoardComment bco = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bco = new BoardComment();
				
				bco.setCno(cno);
				bco.setBid(rset.getInt("BID"));
				bco.setCcontent(rset.getString("CCONTENT"));
				bco.setCwriter(rset.getString("USERNAME"));
				bco.setCdate(rset.getDate("CDATE"));
				bco.setRefcno(rset.getInt("REFCNO"));
				bco.setClevel(rset.getInt("CLEVEL"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bco;
	}

	public int updateComment(Connection con, BoardComment bco) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateComment");

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bco.getCcontent());
			pstmt.setInt(2, bco.getCno());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}
	
	public int deleteComment(Connection con, int cno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteComment");

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}
}
