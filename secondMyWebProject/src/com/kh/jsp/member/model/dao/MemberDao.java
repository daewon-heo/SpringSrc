package com.kh.jsp.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.vo.Member;
import static com.kh.jsp.common.JDBCTemplate.*;

public class MemberDao {
	private Properties prop;
	
	public MemberDao(){
		prop = new Properties();
		
		String filePath = MemberDao.class.getResource("/config/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection con, Member m) throws MemberException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			
			throw new MemberException(e.getMessage());
			// e.printStackTrace();
		
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public Member selectMember(Connection con, Member m) throws MemberException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = new Member();
				
				result.setUserId(rset.getString(1));
				result.setPassword(rset.getString(2));
				result.setUserName(rset.getString(3));
				
				result.setGender(rset.getString("GENDER"));
				result.setAge(rset.getInt("age"));
				result.setEmail(rset.getString("email"));
				result.setPhone(rset.getString("phone"));
				result.setAddress(rset.getString("address"));
				result.setHobby(rset.getString("hobby"));
				result.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
			
		} catch (SQLException e) {
			
			// e.printStackTrace();
			throw new MemberException(e.getMessage());
			
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection con, Member m) throws MemberException {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getHobby());
			pstmt.setString(7, m.getUserId());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		
			//e.printStackTrace();
			throw new MemberException(e.getMessage());
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	
	public int deleteMember(Connection con, String userId) throws MemberException {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
		
			//e.printStackTrace();
			throw new MemberException(e.getMessage());
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public int idDupCheck(Connection con, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = prop.getProperty("idDupCheck");
		
		try {
		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) result = rset.getInt(1);
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

}

















