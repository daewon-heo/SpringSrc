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

import org.apache.ibatis.session.SqlSession;

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

	public int insertComment(SqlSession ses, BoardComment bco) {
		
		return ses.insert("Comment_mapper.insertComment", bco);
	}

	public ArrayList<BoardComment> selectList(SqlSession ses, int bid) {
		
		return new ArrayList<BoardComment>(ses.selectList("Comment_mapper.selectList", bid));
	}

	public BoardComment selectOne(SqlSession ses, int cno) {
		
		return ses.selectOne("Comment_mapper.selectOne", cno);
	}

	public int updateComment(SqlSession ses, BoardComment bco) {
		
		return ses.update("Comment_mapper.updateComment", bco);
	}
	
	public int deleteComment(SqlSession ses, int cno) {

		return ses.update("Comment_mapper.deleteComment", cno);
	}
}
