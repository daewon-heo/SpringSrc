package com.kh.jsp.member.model.service;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

import static com.kh.jsp.common.JDBCTemplate.*;
import static com.kh.jsp.common.MySqlSessionFactory.getSqlSessionFactory;

public class MemberService {

	private MemberDao mDao = new MemberDao();
	
	
	public int insertMember(Member m) throws MemberException {
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = mDao.insertMember(ses, m);
		
		if(result > 0) ses.commit();
		else  ses.rollback();
		
//		close(con);
		ses.close();
		
		return result;
	}


	public Member selectMember(Member m) throws MemberException {
		
		/*Connection con = getConnection();*/
		
		// openSession() : 안의 참/거짓 결과에 따라서 자동 커밋이 활성화 / 비활성화 된다.
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		Member result = mDao.selectMember(ses, m);
		
//		close(con);
		ses.close();
		
		if(result == null) throw new MemberException("아이디나 비밀번호가 일치하는 회원 없음");
		
		return result;
	}


	public int updateMember(Member m) throws MemberException {
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = mDao.updateMember(ses, m);
		
		if(result > 0) ses.commit();
		else  ses.rollback();
		
//		close(con);
		ses.close();
		
		return result;
	}


	public int deleteMember(String userId) throws MemberException {
		
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = mDao.deleteMember(ses, userId);
		
		if(result > 0) ses.commit();
		else  ses.rollback();
		
		/*close(con);*/
		ses.close();
		
		return result;
	}


	public int idDupCheck(String id) {
		/*Connection con = getConnection();*/
		SqlSession ses = getSqlSessionFactory().openSession(false);
		
		int result = mDao.idDupCheck(ses, id);
		
		/*close(con);*/
		ses.close();
		
		return result;
	}
	
}












