package com.kh.jsp.notice.model.dao;

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

import org.apache.ibatis.session.SqlSession;

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
	
	
	public ArrayList<Notice> selectList(SqlSession ses){
		
		return new ArrayList<Notice>(ses.selectList("Notice_mapper.selectList"));
	}


	public Notice selectOne(SqlSession ses, int nno) {

		return ses.selectOne("Notice_mapper.selectOne", nno);
	}


	public int updateCount(SqlSession ses, int nno) {
		
		return ses.update("Notice_mapper.updateCount", nno);
	}


	public int insertNotice(SqlSession ses, Notice n) {
		
		return ses.insert("Notice_mapper.insertNotice", n);
	}


	public int updateNotice(SqlSession ses, Notice n) {

		return ses.update("Notice_mapper.updateNotice", n);
	}


	public ArrayList<Notice> searchNotice(SqlSession ses, String condition, String keyword) {
		
//		String mapper = null;
//		
//		switch(condition) {
//		case "writer" :
//			mapper = "Notice_mapper.searchWriterNotice";
//			break;
//		case "title" :
//			mapper = "Notice_mapper.searchTitleNotice";
//			break;
//		case "content" :
//			mapper = "Notice_mapper.searchContentNotice";
//			break;
//		}
//		
//		return new ArrayList<Notice>(ses.selectList(mapper, keyword));
		HashMap<String, String> hmap = new HashMap<>();
		
		hmap.put(condition, keyword);
		
		return new ArrayList<Notice>(ses.selectList("Notice_mapper.searchList", hmap));
	}


	public ArrayList<Notice> searchNotice(SqlSession ses, HashMap<String, String> hmap) {

		return new ArrayList<Notice>(ses.selectList("Notice_mapper.searchNotice", hmap));
	}
}













