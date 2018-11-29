package com.kh.jsp.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.vo.Member;

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

	public int insertMember(SqlSession ses, Member m) throws MemberException {
		
		int result = ses.insert("Member_mapper.insertMember", m);

		return result;
	}

	public Member selectMember(SqlSession ses, Member m) throws MemberException {
		// 반환값이 객체이다.
		// selectOne() : 객체 하나를 가져오는 메소드
		// selectList() : 객체 배열을 가져오는 메소드
		// selectMap() : 특정 맵 객체를 가져오는 메소드
		// -------------------------------------------
		// 반환값이 int형 값이다.
		// insert() : 객체 정보 추가 시 사용하는 메소드
		// update() : 객제 정보 수정 시 사용하는 메소드
		// delete() : 객체 정보 삭제 시 사용하는 메소드
		return ses.selectOne("Member_mapper.selectMember", m);
	}

	public int updateMember(SqlSession ses, Member m) throws MemberException {

		int result = ses.update("Member_mapper.updateMember", m);
		
		return result;
	}
	
	public int deleteMember(SqlSession ses, String userId) throws MemberException {

		int result = ses.delete("Member_mapper.deleteMember", userId);
		
		return result;
	}

	public int idDupCheck(SqlSession ses, String id) {

		HashMap<String, Object> hmap
			= new HashMap<String, Object>();
		
		hmap.put("userId", id);
		
		ses.selectOne("Member_mapper.idDupCheck", hmap);
		
		return (int)hmap.get("result");
	}

}

















