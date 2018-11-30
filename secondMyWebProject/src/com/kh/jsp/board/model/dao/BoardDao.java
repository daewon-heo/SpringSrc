package com.kh.jsp.board.model.dao;

import static com.kh.jsp.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.jsp.board.model.vo.Board;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao(){
		String filePath = BoardDao.class
				.getResource("/config/board-query.properties").getPath();
		try {
		
			prop.load(new FileReader(filePath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public int getListCount(SqlSession ses){
		
		return ses.selectOne("Board_mapper.getListCount");
	}

	public ArrayList<Board> selectList(SqlSession ses, int currentPage, int limit) {
		// 게시판은 페이징 처리를 위한 시작 페이지번호와 끝 페이지 번호를 가져야 한다.
		// 일반적인 페이징 처리
		/*HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		hmap.put("startRow", startRow);
		hmap.put("endRow", endRow);
		
		return new ArrayList<Board>(ses.selectList("Board_mapper.selectList",hmap));*/
		
		// 마이바티스의 ROWBOUNDS를 활용한 페이징 처리
		int startRow = (currentPage - 1) * limit;
		RowBounds rows = new RowBounds(startRow, limit);
		
		return new ArrayList<Board>(ses.selectList("Board_mapper.selectRowBounds", null, rows));
	}

	public int insertBoard(SqlSession ses, Board b) {
		System.out.println("insertboard");
		return ses.insert("Board_mapper.insertBoard", b);
	}

	public Board selectOne(SqlSession ses, int bid) {
		
		return ses.selectOne("Board_mapper.selectOne", bid);
	}

	public int updateBoard(SqlSession ses, Board b) {
		
		return ses.update("Board_mapper.updateBoard", b);
	}

	public int deleteBoard(SqlSession ses, int bid) {
		System.out.println("deleteboard : " + bid);
		return ses.update("Board_mapper.deleteBoard", bid);
	}

	public ArrayList<Board> top5(SqlSession ses) {
		
		return new ArrayList<Board>(ses.selectList("Board_mapper.top5"));
	}
	
}



















